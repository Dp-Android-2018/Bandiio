package m.dp.onlineshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import m.dp.onlineshop.R;

public class SliderAdapter extends PagerAdapter {

    private Context mcontext ;
    private int[] mimageIds= new int[]{R.drawable.ic_1, R.drawable.ic_2 ,
            R.drawable.ic_3 ,R.drawable.ic_4 ,
            R.drawable.ic_5 ,R.drawable.ic_6 ,
            R.drawable.ic_7 , R.drawable.ic_8,
            R.drawable.ic_9 ,R.drawable.ic_10};

    SliderAdapter(Context context){
        mcontext=context ;
    }
    @Override
    public int getCount() {
        return mimageIds.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView=new ImageView(mcontext) ;
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mimageIds[position]);
        container.addView(imageView,0);
        return imageView ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
