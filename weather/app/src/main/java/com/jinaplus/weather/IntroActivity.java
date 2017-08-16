package com.jinaplus.weather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class IntroActivity extends AppCompatActivity {
    private final String imageSrc = "https://www.kweather.co.kr/icon/12icon/icon01.png";
    private final String weblink = "https://jinaplus.github.io";
    private KakaoLink kakaoLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        try {
            kakaoLink = KakaoLink.getKakaoLink(getApplicationContext());
        } catch (KakaoParameterException e) {
            alert(e.getMessage());
        }

        Button shareBtn = (Button) findViewById(R.id.share_button);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKakaoTalkLink("카카오 공유하기 테스트", weblink + "/search/seoul");
            }
        });

    }

    private void sendKakaoTalkLink(String msg, String weblink_url) {
        try {
            KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

//            kakaoTalkLinkMessageBuilder.addText(msg);
//
//            kakaoTalkLinkMessageBuilder.addImage(imageSrc, 300, 200);

            // 앱이 설치되어 있는 경우 kakao<app_key>://kakaolink?execparamkey1=1111 로 이동.
            // 앱이 설치되어 있지 않은 경우 market://details?id=com.kakao.sample.kakaolink&referrer=kakaotalklink
            // 또는 https://itunes.apple.com/app/id12345로 이동
//            kakaoTalkLinkMessageBuilder.addAppLink(getString(R.string.kakaolink_applink),
//                    new AppActionBuilder()
//                            .addActionInfo(AppActionInfoBuilder.createAndroidActionInfoBuilder()
//                                    .setExecuteParam("execparamkey1=1111").setMarketParam("referrer=kakaotalklink").build())
//                            .addActionInfo(AppActionInfoBuilder.createiOSActionInfoBuilder(AppActionBuilder.DEVICE_TYPE.PHONE)
//                                    .setExecuteParam("execparamkey1=1111").build()).build()
//            );

            // 웹싸이트에 등록한 "http://www.kakao.com"을 overwrite함. overwrite는 같은 도메인만 가능.
                kakaoTalkLinkMessageBuilder.addWebLink("블로그로 이동", "http://blog.naver.com/anwun/221068720388");

             //웹싸이트에 등록한 "http://www.kakao.com"으로 이동.
                kakaoTalkLinkMessageBuilder.addWebButton("서울 날씨 알아보기", weblink_url);

            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, this);
        } catch (KakaoParameterException e) {
            alert(e.getMessage());
        }
    }

    private void alert(String message) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .create().show();
    }
}
