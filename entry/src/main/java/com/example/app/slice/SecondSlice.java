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

public class SecondSlice extends AbilitySlice {
    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00202, "SecondSlice");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_second);

        RoundedImage roundedImage1 = (RoundedImage) findComponentById(ResourceTable.Id_second_image1);
        roundedImage1.setPixelMapToCircleImage(ResourceTable.Media_photo);

        RoundedImage roundedImage2 = (RoundedImage) findComponentById(ResourceTable.Id_second_image2);
        roundedImage2.setPixelMapToCircleImage(ResourceTable.Media_photo1);

        RoundedImage roundedImage3 = (RoundedImage) findComponentById(ResourceTable.Id_second_image3);
        roundedImage3.setPixelMapToCircleImage(ResourceTable.Media_photo2);

        Text text = (Text) findComponentById(ResourceTable.Id_second_text);
        text.setText("circle");
        text.setTextColor(Color.BLACK);
        text.setTextSize(100);

        Button button = (Button) findComponentById(ResourceTable.Id_second_button);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                ThirdSlice thirdSlice = new ThirdSlice();
                Intent intent1 = new Intent();
                present(thirdSlice, intent1);
            }
        });
    }
}
