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
            String translatedMessage = state.getTranslatedText().get(0);
            // 使用HTML格式化翻译的消息，并添加超链接
            String messageWithLink = "<html>" + translatedMessage +
                    "<br><br>Translated by <a href=''>Yandex Translate</a></html>";

            // 创建一个JLabel以支持HTML格式
            JLabel messageLabel = new JLabel(messageWithLink);
            messageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // 添加鼠标点击事件监听器以打开超链接
            messageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openWebPage("https://translate.yandex.com/");
                }
            });

            // 显示弹窗
            JOptionPane.showMessageDialog(null, messageLabel, "Translated Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Translation failed: " + state.getError(), "Error", JOptionPane.ERROR_MESSAGE);
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
