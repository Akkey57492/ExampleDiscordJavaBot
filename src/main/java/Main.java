// jda
import net.dv8tion.jda.api.JDA; // Botの動作に必要
import net.dv8tion.jda.api.JDABuilder; // Botの操作に必要
import net.dv8tion.jda.api.entities.Activity; // Activity操作に必要
import net.dv8tion.jda.api.entities.Message; // Messageの取得に必要
import net.dv8tion.jda.api.entities.User; // Userの取得に必須
import net.dv8tion.jda.api.events.ReadyEvent; // ReadyEventを受け取るために必要
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter; // ListenerAdapterの継承と使用に必須
// jda

//jetbrains
import org.jetbrains.annotations.NotNull; // @NotNullを使うために必要
//jetbrains

// javax
import javax.security.auth.login.LoginException; // throwsのLoginExceptionに使用
// javax

// main class
public class Main extends ListenerAdapter { // Mainクラス(ListenerAdpterを継承)
    public static void main(String[] args) throws LoginException { // main関数
        JDA jda = JDABuilder.createDefault("Token").build(); // ログイン
        jda.addEventListener(new Main()); // イベントを使うための処理(Mainクラスを指定)
        jda.getPresence().setActivity(Activity.playing("Text HERE")); // ゲームプレイを設定
    }

    // events
    @Override
    public void onReady(@NotNull ReadyEvent event) { // Botがログインできた場合のイベント
        System.out.println("Logged In"); // コンソールに「Logged In」と出力
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) { // メッセージを受信したときのイベント
        User user = event.getAuthor(); // メッセージの送信者を取得
        if(user.isBot()) { // 送信者がBotだった場合のイベント
            return; // 無視
        }
        Message msg = event.getMessage(); // メッセージを取得
        if(msg.getContentRaw().equals("!test")) { // メッセージ内容が「!test」だった場合
            event.getChannel().sendMessage("Test!").queue(); // チャンネルに「Test!」と送信
        } else { // そうでなければ
            event.getChannel().sendMessage(msg.getContentRaw()).queue(); // オウム返し
        }
    }
    // events
}
// main class
