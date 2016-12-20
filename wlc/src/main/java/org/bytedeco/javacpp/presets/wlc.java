package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 *
 * @author Jérémy Laviole
 */
@Properties(target = "org.bytedeco.javacpp.wlc", helper = "org.bytedeco.javacpp.helper.wlc",
        value = {
            @Platform(value = "linux-x86_64",
                    include = {"<wlc/wlc.h>",
                        "<wlc/wlc-wayland.h>",
                        "<wlc/wlc-render.h>",
                        "<wlc/geometry.h>"
                    },
                    link = {"wlc@"})
        })

@Platform(include = {"<linux/input>", "<sys/types.h>"})

public class wlc implements InfoMapper {

    public void map(InfoMap infoMap) {

        infoMap.put(new Info("defined __GNUC__").define(false))
                //                .put(new Info("wlc_handle*").cast().pointerTypes("Pointer"))
                .put(new Info("WLC_NONULL", "WLC_PURE").cppTypes().annotations().cppText(""))

                // Defined in the helper
                .put(new Info("wlc_view_from_surface").skip())
                
                .put(new Info("wl_interface").cast().pointerTypes("Pointer"))
                .put(new Info("wl_client").cast().pointerTypes("Pointer"))
                .put(new Info("wl_client*").cast().pointerTypes("Pointer"))
                .put(new Info("pid_t").cast().valueTypes("int"))
                .put(new Info("wlc_handle").cast().pointerTypes("Pointer"));
    
            //                .put(new Info("void (*cb)").skip().pointerTypes("Cb_Pointer"))
            
        
        // pid_t is both a value and a pointer :[ ?
    }
    
    
}
