Spring JWT with an Oauth2 server.

It generates the JWT tokens so that you can authenticate the user in the frontend. 
Then you use this token the whole time in every frontend request, and 
you don't need username + password until the token expires, or the user logs out (which means to delete the token).

Spring security 6+
Spring boot 3.1

Commands to create the key:

Create Keypair: openssl genrsa -out keypair.pem 2048
Create public key from the keypair: openssl rsa -in keypair.pem -pubout -out public.pem
Create private key from the keypair: openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

Those are then injected via RsaKeyProperties from the application.yml file.
In SecurityConfiguration commented code was a less clean approach. 
I left it for comparison.

Spring JWT with an Oauth2 server. It generates the JWT tokens so that you can authenticate the user in the frontend. Then you use this token the whole time in every frontend request and you don't need username + password until the token expires, or the user logs out (which means to delete the token).
•	Spring security 6+
•	Spring boot 3.1
Commands to create the key:
•	Create Keypair: openssl genrsa -out keypair.pem 2048
•	Create public key from the keypair: openssl rsa -in keypair.pem -pubout -out public.pem
•	Create private key from the keypair: openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
