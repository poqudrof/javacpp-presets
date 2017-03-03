#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" librealsense
    popd
    exit
fi

LIBREALSENSE_VERSION=1.12.1
download https://github.com/IntelRealSense/librealsense/archive/v$LIBREALSENSE_VERSION.zip librealsense-$LIBREALSENSE_VERSION.zip

mkdir -p $PLATFORM
cd $PLATFORM
mkdir -p include lib bin
unzip -o ../librealsense-$LIBREALSENSE_VERSION.zip


cd librealsense-$LIBREALSENSE_VERSION

case $PLATFORM in
    linux-x86)
#        patch -Np1 < ../../../librealsense-$LIBREALSENSE_VERSION-linux.patch
	cmake -DBUILD_UNIT_TESTS=false .
        CC="gcc -m32" CXX="gcc -m32" make -j $MAKEJ
        cp -R librealsense.so* ../lib/
        cp -R include/* ../include/
        ;;
    linux-x86_64)
#        patch -Np1 < ../../../librealsense-$LIBREALSENSE_VERSION-linux.patch
	cmake -DBUILD_UNIT_TESTS=false .
        CC="gcc -m64" CXX="gcc -m64" make -j $MAKEJ
        cp -R librealsense.so* ../lib/
        cp -R include/* ../include/
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd ../..
