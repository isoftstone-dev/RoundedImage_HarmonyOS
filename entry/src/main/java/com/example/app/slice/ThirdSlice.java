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

public class ThirdSlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00203, "ThirdSlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_third);

        RoundedImage roundedImage1 = (RoundedImage) findComponentById(ResourceTable.Id_third_image1);
        roundedImage1.setPixelMapToOvalImage(ResourceTable.Media_photo3);

        RoundedImage roundedImage2 = (RoundedImage) findComponentById(ResourceTable.Id_third_image2);
        roundedImage2.setPixelMapToOvalImage(ResourceTable.Media_photo4);

        RoundedImage roundedImage3 = (RoundedImage) findComponentById(ResourceTable.Id_third_image3);
        roundedImage3.setPixelMapToOvalImage(ResourceTable.Media_photo5);

        Text text = (Text) findComponentById(ResourceTable.Id_third_text);
        text.setText("oval");
        text.setTextColor(Color.BLACK);
        text.setTextSize(100);

        Button button = (Button) findComponentById(ResourceTable.Id_third_button);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                MainAbilitySlice mainAbilitySlice = new MainAbilitySlice();
                Intent intent1 = new Intent();
                present(mainAbilitySlice, intent1);
            }
        });
    }
}
