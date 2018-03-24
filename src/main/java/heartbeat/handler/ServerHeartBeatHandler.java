package heartbeat.handler;

import com.simon.heartbeat.common.RequestVo;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

public class ServerHeartBeatHandler extends ChannelHandlerAdapter{

    private static final String SUCCESS_KEY = "OK";

    private static HashMap<String,Object> AUTH_IP_MAP = new HashMap<String, Object>();

    static {
        AUTH_IP_MAP.put("10.8.0.124","123");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            auth(ctx,msg);
        } else if (msg instanceof RequestVo) {
            RequestVo vo = (RequestVo)msg;
            System.out.println(vo.toString());
            ctx.writeAndFlush("info received...");
        } else {
            ctx.writeAndFlush("connect failure!!!!").addListener(ChannelFutureListener.CLOSE);
        }
    }

    private boolean auth(ChannelHandlerContext ctx, Object msg) {
        String str = (String)msg;
        String[] ret = str.split(",");
        String password = (String)AUTH_IP_MAP.get(ret[0]);
        if (password!=null && password.equals(ret[1])) {
            ctx.writeAndFlush(SUCCESS_KEY);
            return true;
        } else {
            ctx.writeAndFlush("failure").addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }


}
