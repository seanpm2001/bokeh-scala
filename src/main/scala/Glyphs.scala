package org.continuumio.bokeh

sealed abstract class BaseGlyph extends PlotObject

class AnnularWedge extends BaseGlyph with FillProps with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object inner_radius extends DataSpec[this.type](this) // with Radius
    object outer_radius extends DataSpec[this.type](this) // with Radius
    object start_angle extends DataSpec[this.type](this)
    object end_angle extends DataSpec[this.type](this)
    object direction extends Field[this.type, Direction](this)
}

class Annulus extends BaseGlyph with FillProps with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object inner_radius extends DataSpec[this.type](this) // with Radius
    object outer_radius extends DataSpec[this.type](this) // with Radius
}

class Arc extends BaseGlyph with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object radius extends DataSpec[this.type](this) // with Radius
    object start_angle extends DataSpec[this.type](this)
    object end_angle extends DataSpec[this.type](this)
    object direction extends Field[this.type, Direction](this)
}

class Bezier extends BaseGlyph with LineProps {
    object x0 extends DataSpec[this.type](this)
    object y0 extends DataSpec[this.type](this)
    object x1 extends DataSpec[this.type](this)
    object y1 extends DataSpec[this.type](this)
    object cx0 extends DataSpec[this.type](this)
    object cy0 extends DataSpec[this.type](this)
    object cx1 extends DataSpec[this.type](this)
    object cy1 extends DataSpec[this.type](this)
}

class Image extends BaseGlyph {
    // object image extends DataSpec[this.type](this)
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object dw extends DataSpec[this.type](this)
    object dh extends DataSpec[this.type](this)
    object palette extends DataSpec[this.type](this)
}

class ImageURI extends BaseGlyph {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object angle extends DataSpec[this.type](this)
    // object url extends DataSpec[this.type, String](this)
}

class ImageRGBA extends BaseGlyph {
    object image extends DataSpec[this.type](this)
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object dw extends DataSpec[this.type](this)
    object dh extends DataSpec[this.type](this)
}

class Line extends BaseGlyph with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
}

class MultiLine extends BaseGlyph with LineProps {
    object xs extends DataSpec[this.type](this)
    object ys extends DataSpec[this.type](this)
}

class Oval extends BaseGlyph with FillProps with LineProps {
    object width extends DataSpec[this.type](this)
    object height extends DataSpec[this.type](this)
    object angle extends DataSpec[this.type](this)
}

class Patch extends BaseGlyph with FillProps with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
}

class Patches extends BaseGlyph with LineProps with FillProps {
    object xs extends DataSpec[this.type](this)
    object ys extends DataSpec[this.type](this)
}

class Quad extends BaseGlyph with FillProps with LineProps {
    object left extends DataSpec[this.type](this)
    object right extends DataSpec[this.type](this)
    object bottom extends DataSpec[this.type](this)
    object top extends DataSpec[this.type](this)
}

class Quadratic extends BaseGlyph with LineProps {
    object x0 extends DataSpec[this.type](this)
    object y0 extends DataSpec[this.type](this)
    object x1 extends DataSpec[this.type](this)
    object y1 extends DataSpec[this.type](this)
    object cx extends DataSpec[this.type](this)
    object cy extends DataSpec[this.type](this)
}

class Ray extends BaseGlyph with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object angle extends DataSpec[this.type](this)
    object length extends DataSpec[this.type](this)
}

class Rect extends BaseGlyph with FillProps with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object width extends DataSpec[this.type](this)
    object height extends DataSpec[this.type](this)
    object angle extends DataSpec[this.type](this)
}

class Segment extends BaseGlyph with LineProps {
    object x0 extends DataSpec[this.type](this)
    object y0 extends DataSpec[this.type](this)
    object x1 extends DataSpec[this.type](this)
    object y1 extends DataSpec[this.type](this)
}

class Text extends BaseGlyph with TextProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    // object text extends DataSpec[this.type, String](this)
    object angle extends DataSpec[this.type](this)
}

class Wedge extends BaseGlyph with FillProps with LineProps {
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object radius extends DataSpec[this.type](this) // with Radius
    object start_angle extends DataSpec[this.type](this)
    object end_angle extends DataSpec[this.type](this)
    object direction extends Field[this.type, Direction](this)
}

trait Marker extends FillProps with LineProps { self: BaseGlyph =>
    object x extends DataSpec[this.type](this)
    object y extends DataSpec[this.type](this)
    object size extends DataSpec[this.type](this) {
        // override val units = Units.Screen
        // override val default = 4
        // override val min_value = 0
    }
}

class Circle extends BaseGlyph with Marker {
    // object radius extends DataSpec(units="data", default=4, min_value=0)
}

class Square extends BaseGlyph with Marker {
    object angle extends DataSpec[this.type](this)
}

class Triangle extends BaseGlyph with Marker

class Cross extends BaseGlyph with Marker

class Xmarker extends BaseGlyph with Marker

class Diamond extends BaseGlyph with Marker

class InvertedTriangle extends BaseGlyph with Marker

class SquareX extends BaseGlyph with Marker

class Asterisk extends BaseGlyph with Marker

class DiamondCross extends BaseGlyph with Marker

class CircleCross extends BaseGlyph with Marker

class HexStar extends BaseGlyph with Marker

class SquareCross extends BaseGlyph with Marker

class CircleX extends BaseGlyph with Marker
