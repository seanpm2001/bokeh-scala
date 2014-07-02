package io.continuum.bokeh

import play.api.libs.json.{Json,JsValue,JsArray,JsObject,JsNull}

trait Serializer {
    case class Model(id: String, `type`: String, attributes: Map[String, Any], doc: Option[String]) {
        def toJson: JsValue =
            Json.obj("id" -> Json.toJson(id),
                     "type" -> Json.toJson(`type`),
                     "attributes" -> anyToJson(attributes),
                     "doc" -> Json.toJson(doc))
    }

    def anyToJson(obj: Any): JsValue = {
        import Json.toJson
        import Formats._
        obj match {
            case obj: Boolean => toJson(obj)
            case obj: Int => toJson(obj)
            case obj: Double => toJson(obj)
            case obj: String => toJson(obj)
            case obj: Symbol => toJson(obj)
            case obj: Ref => toJson(obj)
            case obj: core.EnumType => toJson(obj)
            case obj: CSSColor => toJson(obj)
            case obj: Percent => toJson(obj)
            case obj: breeze.linalg.DenseVector[Double] => toJson(obj)
            case obj: Seq[_] => JsArray(obj.map(anyToJson))
            case obj: Array[_] => JsArray(obj.map(anyToJson))
            case obj: Map[_, _] =>
                JsObject(obj.toList.map {
                    case (key: String, value) => (key,      anyToJson(value))
                    case (key: Symbol, value) => (key.name, anyToJson(value))
                    case _ => throw new IllegalArgumentException(obj.toString)
                })
            case Some(obj) => anyToJson(obj)
            case None => JsNull
            case obj => throw new IllegalArgumentException(obj.toString)
        }
    }

    def allFieldsWithValues(obj: HasFields): List[(String, Any)] =
        ("type", obj.typeName) :: obj.dirtyFieldsWithValues

    def serializeObjs(objs: List[PlotObject]): String = {
        val models = objs.map(getModel).map(_.toJson)
        Json.stringify(JsArray(models))
    }

    def collectObjs(obj: HasFields): List[PlotObject] = {
        val objs = collection.mutable.ListBuffer[PlotObject]()

        traverse(obj, obj => obj match {
            case _: PlotObject => objs += obj
            case _ =>
        })

        objs.toList
    }

    def traverse(obj: HasFields, fn: PlotObject => Unit) {
        val ids = collection.mutable.HashSet[String]()

        def descendFields(obj: HasFields) {
            allFieldsWithValues(obj).foreach {
                case (_, Some(obj)) => descend(obj)
                case _ =>
            }
        }

        def descend(obj: Any) {
            obj match {
                case obj: PlotObject =>
                    if (!ids.contains(obj.id.value)) {
                        ids += obj.id.value
                        descendFields(obj)
                        fn(obj)
                    }
                case obj: HasFields =>
                    descendFields(obj)
                case obj: List[_] =>
                    obj.map(descend)
                case _ =>
            }
        }

        descend(obj)
    }

    def getModel(obj: PlotObject): Model = {
        val Ref(id, tp) = obj.getRef
        Model(id, tp, replaceWithRefs(allFieldsWithValues(obj)), None)
    }

    def replaceWithRefs(fields: List[(String, Any)]): Map[String, Any] = {
        fields.map {
            case (name, Some(value)) => (name, _replaceWithRefs(value))
            case field => field
        } toMap
    }

    def _replaceWithRefs(obj: Any): Any = obj match {
        case obj: PlotObject => obj.getRef
        case obj: HasFields => replaceWithRefs(allFieldsWithValues(obj))
        case obj: List[_] => obj.map(_replaceWithRefs)
        case obj => obj
    }
}