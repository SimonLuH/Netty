package heartbeat.handler;

import com.simon.heartbeat.common.RequestVo;
import com.simon.heartbeat.common.ResponseVo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter{
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            RequestVo vo = (RequestVo)msg;
            System.out.println("recive message: " + "," + msg.toString());

            ResponseVo responseVo = new ResponseVo();
            responseVo.setId(vo.getId());
            responseVo.setResponseMessage(vo.getRequestMessage());
            ctx.writeAndFlush(responseVo);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
