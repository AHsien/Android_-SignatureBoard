package tw.org.iii.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Administrator on 2018/3/10.
 */

public class MyView2 extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines, lastlines;
    private LinkedList<HashMap<String,Float>> lastline;

    private Paint paint;

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.YELLOW);

        lines = new LinkedList<>();
        lastlines = new LinkedList<>();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(4);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(LinkedList<HashMap<String,Float>> line: lines) {
            for (int i = 1; i < line.size(); i++) {
                HashMap<String, Float> p0 = line.get(i - 1);
                HashMap<String, Float> p1 = line.get(i);
                canvas.drawLine(p0.get("x"), p0.get("y"),
                        p1.get("x"), p1.get("y"), paint);

            }
        }

    }

    public void clear(){
        lines.clear();
        lastlines.clear();
        invalidate();
    }

    public void undo(){
        if(lines.size() != 0) {
            lastline = lines.getLast();
            Log.v("brad", ""+lastline);
            lines.removeLast();
            lastlines.add(lastline);
//            Log.v("brad", ""+lastline);
            invalidate();
        }
    }

    public void redo(){
        if(lastlines.size() != 0) {
            lines.add(lastlines.getLast());
            lastlines.removeLast();
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX();
        float ey = event.getY();

        String action = null;
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            action = "Down";
            LinkedList<HashMap<String,Float>> line = new LinkedList<>();

            HashMap<String,Float> point = new HashMap<>();
            point.put("x", ex); point.put("y", ey);
            line.add(point);
            lines.add(line);

        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            action = "Move";
            HashMap<String,Float> point = new HashMap<>();
            point.put("x", ex); point.put("y", ey);

            lines.getLast().add(point);
            invalidate();
        }

        return true; //super.onTouchEvent(event);
    }
}