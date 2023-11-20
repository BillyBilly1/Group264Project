package app;

import interface_adapter.Channel.ChannelViewModel;
import view.ChannelView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Channel Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建 ChannelViewModel 实例
        // 假设 ChannelViewModel 的构造函数不需要参数，或者您已经有了构造参数
        ChannelViewModel viewModel = new ChannelViewModel();

        // 创建 ChannelView 实例并传递 ViewModel
        ChannelView channelView = new ChannelView(viewModel);

        // 将 ChannelView 添加到 JFrame 中
        frame.getContentPane().add(channelView);

        // 设置 JFrame 的大小
        frame.pack(); // 根据添加的组件调整大小

        // 将窗口置于屏幕中央
        frame.setLocationRelativeTo(null);

        // 显示窗口
        frame.setVisible(true);
    }
}
