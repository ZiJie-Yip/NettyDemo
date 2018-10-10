package com.NettyDemo.demo08.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/10 16:52
 */
@Data
@NoArgsConstructor
public class Session {

    private String userId;
    private String userName;

    public Session(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString(){
        return userId + ":" + userName;
    }

}
