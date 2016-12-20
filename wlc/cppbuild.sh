#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" wlc
    popd
    exit
fi

WLC_VERSION=0.0.7
download https://github.com/Cloudef/wlc/releases/download/v$WLC_VERSION/wlc-$WLC_VERSION.tar.bz2 wlc-$WLC_VERSION.tar.bz2

mkdir -p $PLATFORM
cd $PLATFORM
mkdir -p include lib bin
tar -jxvf ../wlc-$WLC_VERSION.tar.bz2

cd wlc-$WLC_VERSION

case $PLATFORM in
    linux-x86)
        ;;
    linux-x86_64)
	git submodule update --init --recursive # - initialize and fetch submodules
#	mkdir target && cd target               # - create build target directory
	$CMAKE -DCMAKE_BUILD_TYPE=Upstream . -DCMAKE_INSTALL_PREFIX=..    # - run CMake
	patch -p1 < ../../../p1.patch
	make
	make install
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

# cd ../..
