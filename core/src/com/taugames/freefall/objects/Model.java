package com.taugames.freefall.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ShortArray;

public class Model {
    private static EarClippingTriangulator triangulator;
    private static ShapeRenderer shapeRenderer;

    static {
        triangulator = new EarClippingTriangulator();
        shapeRenderer = new ShapeRenderer();
    }

    private Array<Polygon> polygons;

    public Model(Polygon polygon) {
        this(polygon, false);
    }

    public Model(Polygon polygon, boolean convex) {
        if (convex) {
            polygons = new Array<Polygon>();
            polygons.add(polygon);
        } else {
            polygons = triangulate(polygon);
        }
    }

    public Model(Array<Polygon> polygons) {
        this.polygons = polygons;
    }

    public void draw() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(249f / 255, 69f / 255, 225f / 255, 1);
        for (Polygon polygon : polygons) {
            shapeRenderer.polygon(polygon.getTransformedVertices());
        }
        shapeRenderer.end();
    }

    public void changeX(float x) {
        for (int i = 0; i < polygons.size; i++) {
            float[] polygonVertices = polygons.get(i).getTransformedVertices();
            for (int j = 0; j < polygonVertices.length; j += 2) {
                polygonVertices[j] += x;
            }
            polygons.set(i, new Polygon(polygonVertices));
        }
    }

    public void changeY(float y) {
        for (int i = 0; i < polygons.size; i++) {
            float[] polygonVertices = polygons.get(i).getTransformedVertices();
            for (int j = 1; j < polygonVertices.length; j += 2) {
                polygonVertices[j] += y;
            }
            polygons.set(i, new Polygon(polygonVertices));
        }
    }

    public void changeXY(float x, float y) {
        for (int i = 0; i < polygons.size; i++) {
            float[] polygonVertices = polygons.get(i).getTransformedVertices();
            for (int j = 0; j < polygonVertices.length; j++) {
                polygonVertices[j] += (j & 1) == 0 ? x : y;
            }
            polygons.set(i, new Polygon(polygonVertices));
        }
    }

    public void setPolygons(Array<Polygon> polygons) {
        this.polygons = polygons;
    }

    public Array<Polygon> getPolygons() {
        return polygons;
    }

    // SOURCE: https://stackoverflow.com/a/42319276/7300063
    public static Array<Polygon> triangulate(Polygon polygon) {
        float[] vertices = polygon.getTransformedVertices();
        ShortArray triangleVertexIndices = triangulator.computeTriangles(vertices);
        Array<Polygon> triangles = new Array<Polygon>();
        for (int i = 0; i < triangleVertexIndices.size; i += 3) {
            triangles.add(new Polygon(new float[] {
                    vertices[triangleVertexIndices.get(i) * 2], vertices[triangleVertexIndices.get(i) * 2 + 1],
                    vertices[triangleVertexIndices.get(i + 1) * 2], vertices[triangleVertexIndices.get(i + 1) * 2 + 1],
                    vertices[triangleVertexIndices.get(i + 2) * 2], vertices[triangleVertexIndices.get(i + 2) * 2 + 1]
            }));
        }
        return triangles;
    }
}
