![http://lh4.ggpht.com/_9iOLSCIXAU0/S4zwdESvl9I/AAAAAAAACl8/A3JbqXOIiC8/s800/squeakwithandroid.gif](http://lh4.ggpht.com/_9iOLSCIXAU0/S4zwdESvl9I/AAAAAAAACl8/A3JbqXOIiC8/s800/squeakwithandroid.gif)
# Squeak on Android #

## Introduction ##

This is the home of the Squeak-On-Android project as introduced by Andreas Raab on the Vm-dev-Mailinglist on January 18th, 2010.<br />
http://lists.squeakfoundation.org/pipermail/vm-dev/2010-January/003684.html

If you just want to try it out without installing the development-tools, go to the android-market and search for "squeak".

## Details ##
The following Details are exerpts from E-Mails/ Posts to the mailinglist from Andreas.

Please be aware that this is not a full port yet. It's a weekend effort
to show the basic feasibility. Lots (and I mean **lots**) of things are
still missing from a full port (among those is text input and network
support to name just two of the more glaring ones). However, I would be
**very** interested to hear if (and how well) it works for other
Android-based cell phones. So if you have a Motorola Droid or or a
T-Mobile G1 give it a shot and post some benchmark results.

Performance on the Nexus One is about what one would expect: With
roughly 1M sends/sec and 30M bytecodes/sec it's not exactly rocking but
it's quite usable for most tasks on a mobile device. (Input is
**terrible** though; Squeak's UI is not made for fat-fingered clicks like
mine :-)

### Setup The Development-Environment ###

The VM itself is a shared library (libsqueakvm.so) the "app" is a
wrapper written in Java.

You'll need the Android NDK in order to compile the shared lib. Make
sure you can compile hellojni or some of the others to ensure your tools
are in place.

Place the entire squeakvm directory in the NDK's apps/ directory (next
to hellojni and friends)

To compile the app you need Eclipse and the various SDK bits (see
Android docs for that; it works pretty much as advertised)

Apparently, Eclipse does not "see" the dependencies between the shared
library and the app - you need to manually remake the shared lib if
you've changed a .C file

Make absolutely sure you how to work the debugger (ddms). It's pretty
much your only source of debug info since it shows the android logs that
you can write to by using either System.out or dprintf().

### Some comments on the implementation ###
The image is broken into pieces since Android's AssetManager doesn't
like assets larger than 1MB. There's probably a better way of doing that
but here's the code I used to break up an image:
```
	infile := FileStream readOnlyFileNamed: 'android.image'.
	infile binary.
	count := 0.
	[infile atEnd] whileFalse:[
		outfile := FileStream newFileNamed: 'android.image.', (count := count+1).
		outfile binary.
		outfile nextPutAll: (infile next: 1024*1024).
		outfile close.
	].
```
All the Android specific code is currently in one file
project/jni/squeakvm.c for simplicity (this should be broken out to
separate files as the project progresses).

Since the image file is compressed, it's preloaded into the object
memory, and then the sqImageFile**and sqAlloc** functions are stubbed out
"just so" that they operate on the preloaded image in-memory. It's a
really neat hack but you probably wouldn't get that from reading the
code :-)

I haven't been using "proper" JNI callbacks for implementing display
updates; but then I don't **really** think that should be necessary (we
should rather fix the Morphic update cycle not to leave bits behind).
But if you want to start with something I'd say a good place is to
implement the callbacks from Squeak to Java in ioForceToScreen and
ioShowDisplay.

## Discussion ##
Discussions on vm-specific issues should take place on
the Squeak vm-dev mailing list. Info at http://lists.squeakfoundation.org/mailman/listinfo/vm-dev also available at http://n4.nabble.com/Squeak-VM-f104410.html or http://news.gmane.org/gmane.comp.lang.smalltalk.squeak.vm.devel

## Screenshot ##
![http://lists.squeakfoundation.org/pipermail/vm-dev/attachments/20100117/0ce8a5a5/AndroidSqueak-0001.png](http://lists.squeakfoundation.org/pipermail/vm-dev/attachments/20100117/0ce8a5a5/AndroidSqueak-0001.png)