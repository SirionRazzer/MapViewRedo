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

    private Bitmap bmpMark, bmpMarkTouch;

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
            this.marks.add(new Mark(i.getX().floatValue(),i.getY().floatValue(),true, i.getType().toString(), i.getType()));
        }
        for(Poi p : pois) {
            this.marks.add(new Mark(p.getX().floatValue(), p.getY().floatValue(), false, p.getName(), InfrastructureType.UNKNOWN));
        }
        return marks;
    }

    private void initLayer() {
        radiusMark = setValue(10f);

        bmpMark = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark);
        bmpMarkTouch = BitmapFactory.decodeResource(mapView.getResources(), R.mipmap.mark_touch);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void onTouch(MotionEvent event) {
        if (marks != null) {
            if (!marks.isEmpty()) {
                float[] goal = mapView.convertMapXYToScreenXY(event.getX(), event.getY());
                for (int i = 0; i < marks.size(); i++) {
                    if (MapMath.getDistanceBetweenTwoPoints(goal[0], goal[1],
                            marks.get(i).getX() - bmpMark.getWidth() / 2, marks.get(i).getY() - bmpMark
                                    .getHeight() / 2) <= 78) {
                        num = i;
                        isClickMark = true;
                        break;
                    }

                    if (i == marks.size() - 1) {
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
                    //mark ico
                    canvas.drawBitmap(bmpMark, goal[0] - bmpMark.getWidth() / 2,
                            goal[1] - bmpMark.getHeight() / 2, paint);
                    if (i == num && isClickMark) {
                        canvas.drawBitmap(bmpMarkTouch, goal[0] - bmpMarkTouch.getWidth() / 2,
                                goal[1] - bmpMarkTouch.getHeight(), paint);
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
