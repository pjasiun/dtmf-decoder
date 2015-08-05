# DTMF decoder

The [Dual-tone multi-frequency signaling](https://en.wikipedia.org/wiki/Dual-tone_multi-frequency_signaling) Android decoder. 

The aplication recognise tones it steps:

 * capture sound from the microfone,
 * transforme it using [Fast Fourier Transform](https://en.wikipedia.org/wiki/Fast_Fourier_transform) (thanks to http://introcs.cs.princeton.edu/java/97data/FFT.java.html),
 * do additional clean-up to reduce noices,
 * find picks on the frequency spectrum,
 * put the reconised number into the text field.
 
The project was exported from http://code.google.com/p/dtmf-decoder to make it simpler to look through or fork.

Code license: [GNU Lesser GPL](http://www.gnu.org/licenses/lgpl.html).

It was created for Android 2.2.3 (Froyo).

The project is no longer maintained.
