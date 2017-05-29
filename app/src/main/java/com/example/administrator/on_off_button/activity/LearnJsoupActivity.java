package com.example.administrator.on_off_button.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.on_off_button.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by chun.yang on 2017/5/24.
 *
 * https://mp.weixin.qq.com/s/1xtmH9EZHwC-3_d-x-oINw
 */

public class LearnJsoupActivity extends AppCompatActivity {

    private EditText mEd;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_jsoup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv = (TextView) findViewById(R.id.tv);
        mEd = (EditText) findViewById(R.id.edit_query);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDocument(mEd.getText().toString());
            }
        });

    }

    private void getDocument(final String page){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Log.i("yc-address", "http://www.qiushibaike.com/8hr/page/" + page);
                    Document document = Jsoup.connect("http://www.qiushibaike.com/8hr/page/" + page).get();
//                    Document document =
//                            Jsoup.connect("http://www.qiushibaike.com/8hr/page/3/")
//                            .data("query","Java")
//                            .userAgent("Mozilla")
//                            .cookie("auth","token")
//                            .timeout(3000)
//                            .post();
                    Elements els = document.select("a.contentHerf");
//                    Log.i("html内容", els.toString());
                    String str = "";
                    Log.i("size", els.size() + "");
                    for(int i=0;i< els.size() ;i++){
                        Element element = els.get(i);
                        Log.i("标题", element.text());
                        str = str + element.text() + "\n";
                        String href = element.attr("href");
                        Log.i("链接", "http://www.qiushibaike.com" + href);
                        Log.i("current-item", "---------------------------"+i + "");

                        str = str + "http://www.qiushibaike.com" + href + "\n";

                        //获取详情页内容
//                        http://www.qiushibaike.com/article/118990361
                        //"http://www.qiushibaike.com" + href
//                        Document doc_detail = null;
//                        try {
//                            doc_detail = Jsoup.connect("http://www.qiushibaike.com/article/118990361")
//                                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4")
//                                    .followRedirects(true)
//                                    .timeout(3000)
//                                    .get();
//
//                            Elements els_detail = doc_detail.select(".content");
//                            Log.i("内容", els_detail.text());
//
//                            //获取图片
//                            Elements els_pic = doc_detail.select(".thumb img[src$=jpg]");
//                            if(! els_pic.isEmpty()){
//                                String pic_src = els_pic.attr("src");
//                                Log.i("图片地址", pic_src);
//                            }else {
//                                Log.i("图片地址", "没有");
//                            }
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
