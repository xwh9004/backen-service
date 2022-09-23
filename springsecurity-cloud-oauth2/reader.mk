//创建密钥对
keytool -genkeypair -alias serverkey -keypass 123456 -storepass 123456 \
    -dname "C=CN,ST=GD,L=SZ,O=vihoo,OU=dev,CN=vihoo.com" \
    -keyalg RSA -keysize 2048 -validity 3650 -keystore server.keystore
//生成公钥
keytool -list -rfc --keystore server.keystore | openssl x509 -inform pem -pubkey