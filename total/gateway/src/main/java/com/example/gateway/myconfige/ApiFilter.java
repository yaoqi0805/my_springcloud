package com.example.gateway.myconfige;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;

/*服务网关还有个作用就是接口的安全性校验，这个时候我们就需要通过
  zuul 进行统一拦截，zuul 通过继承过滤器 ZuulFilter 进行处理*/
//@Component
public class ApiFilter extends ZuulFilter {

    /*filterType 为过滤类型，可选值有 pre（路由之前）、routing(路由之时)、
    post(路由之后)、error路由之后*/
    @Override
    public String filterType() {
        return "pre";
    }

    /*filterOrdery 为过滤的顺序，如果有多个过滤器，则数字越小越先执行*/
    @Override
    public int filterOrder() {
        return 0;
    }

    //shouldFilter 表示是否过滤，这里可以做逻辑判断，true 为过滤，false 不过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /*run 为过滤器执行的具体逻辑，在这里可以做很多事情，比如：权限判断、合法性
            校验等*/
    @Override
    public Object run() throws ZuulException {
        System.out.println("你好你好你好");
        //这里写校验代码
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token");
        if(!"22".equals(token)){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                context.getResponse().getWriter().write("token is invalid.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
