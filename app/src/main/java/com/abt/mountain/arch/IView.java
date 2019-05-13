package com.abt.mountain.arch;

/**
 * Author: WeiQi
 * Date: 2019/4/29 17:05
 * Description:
 */
public interface IView<VM extends IViewModel, TM> {
    void setViewModel(VM viewModel);
    void setToolbarViewModel(TM toolbarViewModel);
}
