package cn.misection.miscourse.model;

import java.util.List;

import cn.misection.miscourse.presenter.MinePresenter;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName MineModel
 * @Description TODO
 * @CreateTime 2021年03月11日 00:45:00
 */
public class MineModel implements IModel
{
    private final MinePresenter presenter;

    public MineModel(MinePresenter presenter)
    {
        this.presenter = presenter;
        init();
    }

    private void init()
    {

    }

    @Override
    public List requireData()
    {
        return null;
    }
}
