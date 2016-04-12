**make sure that you copy the correct libsqueakvm.so** <br />
_android\_ndk\_xyz/apps/squeakvm/project/libs/armeabi/libsqueakvm.sq_ to
the right location in your eclipse-workspace _workspace/SqueakVM/lbs/armeabi/_.
The size of that .so should be about 370 kb. During compilation there will be produced a libsqueakvm.so into a different folder ("out") - that file is much bigger and it won't work!

**refresh the eclipse-workspace after copying libsqueakvm.so** <br />
If you want to make sure that eclipse recognizes the new version of the shared library, clean the project.

**I had serious problems with version release 3 of the ndk** <br />
I encountered SIGSEGVs in various JNI-calls. With the NDK release 1 everything works fine.
Thread in the android-ndk-group:
http://groups.google.com/group/android-ndk/browse_thread/thread/26b518a374bf4579/ae4d97d1759e349d <br />
_Update_: [r3](https://code.google.com/p/squeak-android-vm/source/detail?r=3) uses GCC 4.4.0 by default (see docs/CHANGES.TXT) but comes with a toolchain containing GCC 4.2.1. To force the usage of GCC 4.2.1 you have to export an environment variable like this:
```
export NDK_TOOLCHAIN=arm-eabi-4.2.1
```
By using this elder version of GCC i did not encounter any SIGSEGV

_Update_: Release Version 5 of the NDK does not contain the GCC 4.2.1 Toolchain, so you should stay with version [r3](https://code.google.com/p/squeak-android-vm/source/detail?r=3). [R3](https://code.google.com/p/squeak-android-vm/source/detail?r=3) was added in the download-section