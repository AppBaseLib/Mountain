package com.abt.mountain.arch;

/**
 * Author: WeiQi
 * Date: 2019/4/29 17:04
 * Description:
 */
public interface IViewModel<N extends INavigator> {
    void initialize();
    void setNavigator(N navigator);
}
