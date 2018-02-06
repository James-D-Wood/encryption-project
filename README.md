# encryption-project
As a way of getting our class excited about matrix multiplication, my linear algebra instructor introduced us to a series of excel macros that could be used for encoding and then encrypting text through this operation. The entire process was cumbersome and time consuming, but the results it yielded were impressive enough to get me to thinking about whether it could be simplified. I used to pass notes in elementary school with ciphers I had developed with some classmates. So I was immediately fascinated by the unbreakable nature of the code we had created and wanted to recreate it as a sort of programmer's version of the "secret communications" the kids from Stranger Things would be proud of. What makes an encryptor more secure than an encoder is that it will generate one of many possible "random" strings of characters for every unique input as opposed to just the one that an encoder will provide. Once the encoding is worked out for one message, it is fairly easy to decode any other data encoded with the same technique. With encryption however, the key must must be guessed from many, many combinations and is unique to each encryption, so that the safety of one is not dependent on another. My simple encryptor can will randomly generate one out of 9^9 (387,420,489) keys. 

# Use Cases
The program was initially used to "pass notes" over email with a peer of mine, but I have since found it useful for encrypting documents I would like to keep away from prying eyes (ie: pretty much just a list of passwords that I often forget). When encrypting a string, the program will spit out three values: the encrypted message, the key, and an integer value that the program uses to appropriately decrypt the message. While the three numbers are rather meaningless out of context, I decided to run with the assumption that someone could get a hold of my mechanism for decryption (as I'm posting it here today), and easily undo the work I did to encrypt my message. So I took the system a step further when sending messages to my friend. There is an abundance of ways you can insert the key and integer value among your message to disguise both the encrypted message and the means of unlocking it simultaneously. This way, even a brute force attempt guess at the key would be innocuous.

On a larger scale, encryption is important for protecting customer data, financial information, or sensitive intelligence. That's not to say my Java app is the way to do it, but the concepts could be scaled and modified to piggyback on other applications to make them more secure.  

# Class Descriptions
**Cryptography** -
This class is the main class for the application. It handles user input and output and calls upon the Matrix class to do the heavy lifting in calculations. 

**Code** - 
This class encompasses the Code object that is used for storing, modifying and accessing the three values (encryption, key, rows) that are essential to the encryption/decryption process.

**Matrix** -
Though Java does support multidimensional arrays, I found it was easier to keep the matrix math organized by creating a matrix object to hold arrays and pass through supporting methods. 
