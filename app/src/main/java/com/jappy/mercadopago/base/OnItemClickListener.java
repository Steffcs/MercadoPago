package com.jappy.mercadopago.base;

import com.airbnb.lottie.LottieAnimationView;

public interface OnItemClickListener<T> {
    void onItemClick(int adapterPosition, T item,  LottieAnimationView checkbox);
}
