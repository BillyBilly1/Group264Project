package interface_adapter.translation;

import interface_adapter.Channel.ChannelViewModel;
import use_case.translate.TranslateOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TranslatePresenter implements TranslateOutputBoundary {

    private final ChannelViewModel channelViewModel;

    private final TranslateViewModel translateViewModel;

    public TranslatePresenter(ChannelViewModel channelViewModel, TranslateViewModel translateViewModel) {
        this.channelViewModel = channelViewModel;
        this.translateViewModel = translateViewModel;
    }

    @Override
    public void presentTranslationResult(TranslateState state) {
        if (state.isTranslationSuccessful()) {
            channelViewModel.updateTranslatedText(translateViewModel.getState().getTranslatedText());

            // 创建包含可点击链接的 JPanel
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            // 创建包含链接的标签
            JLabel label = new JLabel("<html><a href='https://translate.yandex.com/'>Translated by Yandex Translate</a></html>");
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // 添加链接点击事件监听器
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openWebPage("https://translate.yandex.com/");
                }
            });

            // 将标签添加到面板
            panel.add(label, BorderLayout.CENTER);

            // 显示包含链接的消息框
            JOptionPane.showMessageDialog(null, panel, "Translation Result", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed: " + state.getError(),
                    "Translation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
