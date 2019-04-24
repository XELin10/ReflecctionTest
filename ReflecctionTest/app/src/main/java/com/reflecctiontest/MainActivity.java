package com.reflecctiontest;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {


    //hahhahahahh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String s = null;
        try
        {
            //获取class的Class对象，每个类都会有一个Class，这里Class也是一个类
            //字符串是完整类路径
            Class<?> bookClass = Class.forName("com.reflecctiontest.Book");
            //获得实例方法一
            Object book = bookClass.newInstance();
            //获得实例方法二
//            Constructor con = bookClass.getConstructor();
//            Object book = con.newInstance();

            //获得私有方法
            //第一个参数是方法名，第二个开始原方法getName自带都参数类型
            Method method = bookClass.getDeclaredMethod("getName",String.class);
            //获得该对象的成员变量
            //字符串是成员变量名
            Field f = bookClass.getDeclaredField("name");

            //方法设置城可访问
            method.setAccessible(true);//调用方法前，设置访问标志
            //调用方法
            //第一个参数是要执行该方法都对象，第二个参数开始是要传入方法的参数
            s = (String) method.invoke(book,"name");

            //成员变量设置城可访问
            f.setAccessible(true);
            //修改成员变量
            //第一参数是要持有该成员变量的对象，第二个参数是要修改的数据
            f.set(book,"app");
            //获取成员变量
            //第一参数是要持有该成员变量的对象
            s =  (String)f.get(book);

            //下面是各种异常catch
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
        }catch (ClassNotFoundException e){
        }catch (InstantiationException e){
        }catch (InvocationTargetException e){
        }catch (NoSuchFieldException e){

        }
    }
}
