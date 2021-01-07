package com.soczilla.mapviewredo.library.layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;

import com.soczilla.mapviewredo.library.MapView;
import com.soczilla.mapviewredo.library.model.*;
import com.soczilla.mapviewredo.library.utils.MapMath;
import com.soczilla.mapviewredo.library.R;

import java.util.*;

/**
 * AdvancedMarkLayer
 *
 * @author: SirionRazzer
 */
public class AdvancedMarkLayer extends MapBaseLayer {

    private List<Mark> marks;

    private MarkIsClickListener listener;

    private Bitmap bmpMark,
            bmpMark1,
            bmpMark2,
            bmpMark3,
            bmpMark4,
            bmpMark5,
            bmpMark6sensor,
            bmpMarkTouch;

    private float radiusMark;
    private boolean isClickMark = false;
    private int num = -1;

    private Paint paint;

    public AdvancedMarkLayer(MapView mapView) {
        this(mapView, null, null);
    }

    public AdvancedMarkLayer(MapView mapView, List<Infrastructure> infrastructures, List<Poi> pois) {
        super(mapView);
        this.marks = createMarks(infrastructures, pois);
        initLayer();
    }

    private List<Mark> createMarks(List<Infrastructure> infrastructures, List<Poi> pois) {
        List<Mark> marks = new LinkedList<>();
        for (Infrastructure i : infrastructures) {
            marks.add(new Mark(i.getX().floatValue(),
                    i.getY().floatValue(),
                    true,
                    i.getInfrastructureType().toString(),
                    null,
                    i.getInfrastructureType(),
                    null));
        }
        for (Poi p : pois) {
            marks.add(new Mark(p.getX().floatValue(),
                    p.getY().floatValue(),
                    false,
                    p.getName(),
                    p.getPoiType(),
                    null,
                    p.getDescription()));
        }
        return marks;
    }

    private void initLayer() {
        radiusMark = setValue(10f);

        loadMarks();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private void loadMarks() {
        bmpMark = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark);
        bmpMark1 = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark1);
        bmpMark2 = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark2);
        bmpMark3 = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark3);
        bmpMark4 = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark4);
        bmpMark5 = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark5);
        bmpMark6sensor = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark6sensor);
        bmpMarkTouch = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark_touch);

    }

    @Override
    public void onTouch(MotionEvent event) {
        if (marks != null) {
            if (!marks.isEmpty()) {
                float[] goal = mapView.convertMapXYToScreenXY(event.getX(), event.getY());
                float bestDistance = Float.MAX_VALUE;
                boolean foundClickmark = false;
                int clickmarkIndex = 0;
                for (int i = 0; i < marks.size(); i++) {
                    float distance = MapMath.getDistanceBetweenTwoPoints(goal[0], goal[1],
                            marks.get(i).getX() - bmpMark.getWidth() / 2, marks.get(i).getY() - bmpMark
                                    .getHeight() / 2);

                    if (distance <= 78 && distance <= bestDistance) {
                        bestDistance = distance;
                        clickmarkIndex = i;
                        foundClickmark = true;
                    }

                    if (i == marks.size() - 1) {
                        if (foundClickmark) {
                            num = clickmarkIndex;
                            isClickMark = true;
                            break;
                        }

                        isClickMark = false;
                    }
                }
            }

            if (listener != null && isClickMark) {
                listener.markIsClick(num);
                mapView.refresh();
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Matrix currentMatrix, float currentZoom, float
            currentRotateDegrees) {
        if (isVisible && marks != null) {
            canvas.save();
            if (!marks.isEmpty()) {
                for (int i = 0; i < marks.size(); i++) {
                    PointF mark = new PointF(marks.get(i).getX(), marks.get(i).getY());
                    float[] goal = {mark.x, mark.y};
                    currentMatrix.mapPoints(goal);

                    paint.setColor(Color.BLACK);
                    paint.setTextSize(radiusMark);
                    //mark name
                    if (mapView.getCurrentZoom() > 1.0) {
                        canvas.drawText(marks.get(i).getName(), goal[0] - radiusMark, goal[1] -
                                radiusMark / 2, paint);
                    }
                    //mark ico (should be infra or poi, not both
                    // draw infrastructure mark
                    if (marks.get(i).getInfrastructureType() != null) {
                        switch (marks.get(i).getInfrastructureType()) {
                            case UNKNOWN:
                                canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                                        goal[1] - bmpMark.getHeight() / 2, paint);
                                break;
                            case SENSOR:
                                canvas.drawBitmap(bmpMark6sensor, goal[0] - bmpMark6sensor.getWidth() / 2,
                                        goal[1] - bmpMark6sensor.getHeight() / 2, paint);
                                break;
                            default:
                                canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                                        goal[1] - bmpMark.getHeight() / 2, paint);
                        }
                        canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                                goal[1] - bmpMark.getHeight() / 2, paint);
                    }
                    // draw poi mark

                    if (marks.get(i).getPoiType() != null) {
                        switch (marks.get(i).getPoiType()) {
                            case UNKNOWN:
                                canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                                        goal[1] - bmpMark.getHeight() / 2, paint);
                                break;
                            case RESTAURANT:
                                canvas.drawBitmap(bmpMark1, goal[0] - bmpMark1.getWidth() / 2,
                                        goal[1] - bmpMark1.getHeight() / 2, paint);
                                break;
                            case FOOD_AND_DRINK:
                                canvas.drawBitmap(bmpMark2, goal[0] - bmpMark2.getWidth() / 2,
                                        goal[1] - bmpMark2.getHeight() / 2, paint);
                                break;
                            case GROCERIES:
                                canvas.drawBitmap(bmpMark3, goal[0] - bmpMark3.getWidth() / 2,
                                        goal[1] - bmpMark3.getHeight() / 2, paint);
                                break;
                            case LUXURY_RETAILERS:
                                canvas.drawBitmap(bmpMark4, goal[0] - bmpMark4.getWidth() / 2,
                                        goal[1] - bmpMark4.getHeight() / 2, paint);
                                break;
                            case FASHION:
                                canvas.drawBitmap(bmpMark5, goal[0] - bmpMark5.getWidth() / 2,
                                        goal[1] - bmpMark5.getHeight() / 2, paint);
                                break;
                            default:
                                canvas.drawBitmap(bmpMark1, goal[0] - bmpMark1.getWidth() / 2,
                                        goal[1] - bmpMark1.getHeight() / 2, paint);
                        }
                    }

                    if (i == num && isClickMark) {
                        canvas.drawBitmap(bmpMarkTouch, goal[0] - bmpMarkTouch.getWidth() / 2,
                                goal[1] - bmpMarkTouch.getHeight(), paint);

                        //mark's additional text only for pois
                        if (marks.get(i).getPoiType() != null) {
                            if (mapView.getCurrentZoom() > 1.0 && marks.get(i).getTextExtra() != null) {
                                canvas.drawText(marks.get(i).getTextExtra(), goal[0] - radiusMark, goal[1] -
                                        radiusMark / 2, paint);
                            }
                        }
                    }
                }
            }
            canvas.restore();
        }
    }

    public boolean isClickMark() {
        return isClickMark;
    }

    public void setMarkIsClickListener(MarkIsClickListener listener) {
        this.listener = listener;
    }

    public interface MarkIsClickListener {
        void markIsClick(int num);
    }
}
