package fengkongweishi.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * 建立连接,异步通知报告生成情况
 *
 * @author huanghengkun
 * @date 2018/01/23
 */
@ServerEndpoint("/websocket/{reportId}")
@Component
public class ReportSocket {
    private Session session;
    private Integer reportId;
    private static Map<Integer, ReportSocket> webSockets = new Hashtable<>();

    /**
     * 连接建立成功调用
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("reportId") Integer reportId, Session session) {
        this.session = session;
        this.reportId = reportId;
        webSockets.put(reportId, this);
        System.out.println("新连接加入，reportId：" + reportId);
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() {
        webSockets.remove(reportId);
        System.out.println("连接关闭，reportId:" + reportId);
    }

    /**
     * 异常时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("------------------websocket异常--------------------");
        error.printStackTrace();
    }

    /**
     * 自定义消息发送，给本连接发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
//		同步发送
        this.session.getBasicRemote().sendText(message);
//		异步发送
//		this.session.getAsyncRemote().sendText(message);
    }

    public static void sendMessage(reportSocketMessage message) {
        for (Map.Entry<Integer, ReportSocket> entry : webSockets.entrySet()) {
            try {
                if (message.getReportId().equals(entry.getKey())) {
                    entry.getValue().sendMessage(JSON.toJSONString(message));
                }
            } catch (IOException e) {
                System.out.println("----------web消息通知发送异常----------");
                e.printStackTrace();
            }
        }
    }
}
