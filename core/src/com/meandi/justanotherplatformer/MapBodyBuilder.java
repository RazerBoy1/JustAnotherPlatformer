package com.meandi.justanotherplatformer;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class MapBodyBuilder {
    private static float ppt = 0;

    public static void buildShapes(Map map, World world, float pixels, int layerID) {
        ppt = pixels;
        MapObjects objects = map.getLayers().get(layerID).getObjects();
        //Array<Body> bodies = new Array<>();

        for (MapObject object : objects) {
            if (object instanceof TextureMapObject)
                continue;

            Shape shape;

            if (object instanceof RectangleMapObject)
                shape = getRectangle((RectangleMapObject) object);
            else if (object instanceof PolygonMapObject)
                shape = getPolygon((PolygonMapObject) object);
            else if (object instanceof PolylineMapObject)
                shape = getPolyline((PolylineMapObject) object);
            else if (object instanceof CircleMapObject)
                shape = getCircle((CircleMapObject) object);
            else
                continue;

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bd);
            body.createFixture(shape, 1);

            //bodies.add(body);
            shape.dispose();
        }
    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();

        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / ppt, (rectangle.y + rectangle.height * 0.5f) / ppt);
        polygon.setAsBox(rectangle.width * 0.5f / ppt, rectangle.height * 0.5f / ppt, size, 0.0f);

        return polygon;
    }

    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius / ppt);
        circleShape.setPosition(new Vector2(circle.x / ppt, circle.y / ppt));

        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();

        float[] vertices = polygonObject.getPolygon().getTransformedVertices();
        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            //System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / ppt;
        }

        for (float p : worldVertices)
            System.out.println(p);

        polygon.set(worldVertices);

        return polygon;
    }

    /*private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        Polygon polygon = polygonObject.getPolygon();
        PolygonShape polygonShape = new PolygonShape();
        float[] vertices = polygon.getVertices();

        for (int i = 0; i < vertices.length; i += 2) {
            vertices[i]   = (polygon.getX() + vertices[i]) / ppt;
            vertices[i+1] = (polygon.getY() + vertices[i+1]) * ppt;
        }

        polygonShape.set(vertices);

        for (float p : vertices) {
            System.out.println(p);
        }

        return polygonShape;
    }*/

    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2] / ppt;
            worldVertices[i].y = vertices[i * 2 + 1] / ppt;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);

        return chain;
    }
}