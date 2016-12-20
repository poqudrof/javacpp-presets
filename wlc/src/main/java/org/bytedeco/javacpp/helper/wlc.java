/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jiii
 */

package org.bytedeco.javacpp.helper;

import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;

public class wlc extends org.bytedeco.javacpp.presets.wlc {

/**
 * Turns wl_surface into a wlc view. Returns 0 on failure. This will also trigger view.created callback as any view would.
 * For the extra arguments see details of wl_resource_create and wl_resource_set_implementation.
 * The extra arguments may be set NULL, if you are not implementing Wayland interface for the surface role.
 */
public static native @ByVal @Cast("wlc_handle*") Pointer wlc_view_from_surface(@Cast("wlc_resource") long surface, @Cast("wl_client*") Pointer client, @Cast("const wl_interface*") Pointer interf, @Const Pointer implementation, @Cast("uint32_t") int version, @Cast("uint32_t") int id,  Pointer userdata);

}