package com.example.app.slice;

import com.custom.library.RoundedImage;
import com.example.app.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MainSlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        RoundedImage roundedImage1 = (RoundedImage) findComponentById(ResourceTable.Id_image1);
        roundedImage1.setPixelMapToRoundedRect(ResourceTable.Media_photo, 100, 50, 100, 50);

        RoundedImage roundedImage2 = (RoundedImage) findComponentById(ResourceTable.Id_image2);
        roundedImage2.setPixelMapToRoundedRect(ResourceTable.Media_photo1, 100, 100, 100, 100);

        RoundedImage roundedImage3 = (RoundedImage) findComponentById(ResourceTable.Id_image3);
        roundedImage3.setPixelMapToRoundedRect(ResourceTable.Media_photo2, 50, 100, 50, 100);

        Text text = (Text) findComponentById(ResourceTable.Id_text);
        text.setText("rounded");
        text.setTextColor(Color.BLACK);
        text.setTextSize(100);

        Button button = (Button) findComponentById(ResourceTable.Id_button);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                SecondSlice secondSlice = new SecondSlice();
                Intent intent1 = new Intent();
                present(secondSlice, intent1);
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
