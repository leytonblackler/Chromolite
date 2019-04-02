package com.leytonblackler.chromolite.main.effecthandler.effectplatforms;

import java.util.List;

public interface EffectPlatform {

    void setLayouts(List<int[][]> layouts);

    int[] getLengths();

}
