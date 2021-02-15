FROM openjdk:15-alpine
VOLUME /web
EXPOSE 5050
COPY core/target/modules /app/modules
#COPY core/target/core-1.0-SNAPSHOT.jar  /app/core.jar
COPY core/target/classes /app/core/
ENTRYPOINT [ "java", "--module-path", "/app/core:/app/modules", "-m", "core/x.snowroller.ServerExample"]


#FROM  maven:3.6.3-adoptopenjdk-15 AS build
#USER root
#COPY ./ /src
#RUN mvn -f /src/pom.xml clean package
#RUN jlink \
#--module-path /src/core/target/classes:/src/core/target/modules \
#--strip-java-debug-attributes \
#--compress=2 \
#--add-modules core,java.base,com.google.gson,x.snowroller.fileutils,x.snowroller.spi \
#--output /opt/jdk-mini
#
##FROM frolvlad/alpine-glibc:latest
#FROM alpine:latest
#RUN apk add --no-cache tzdata --virtual .build-deps curl binutils zstd \
#    && GLIBC_VER="2.31-r0" \
#    && ALPINE_GLIBC_REPO="https://github.com/sgerrand/alpine-pkg-glibc/releases/download" \
#    && GCC_LIBS_URL="https://archive.archlinux.org/packages/g/gcc-libs/gcc-libs-10.1.0-2-x86_64.pkg.tar.zst" \
#    && GCC_LIBS_SHA256="f80320a03ff73e82271064e4f684cd58d7dbdb07aa06a2c4eea8e0f3c507c45c" \
#    && ZLIB_URL="https://archive.archlinux.org/packages/z/zlib/zlib-1%3A1.2.11-3-x86_64.pkg.tar.xz" \
#    && ZLIB_SHA256=17aede0b9f8baa789c5aa3f358fbf8c68a5f1228c5e6cba1a5dd34102ef4d4e5 \
#    && curl -LfsS https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub -o /etc/apk/keys/sgerrand.rsa.pub \
#    && SGERRAND_RSA_SHA256="823b54589c93b02497f1ba4dc622eaef9c813e6b0f0ebbb2f771e32adf9f4ef2" \
#    && echo "${SGERRAND_RSA_SHA256} */etc/apk/keys/sgerrand.rsa.pub" | sha256sum -c - \
#    && curl -LfsS ${ALPINE_GLIBC_REPO}/${GLIBC_VER}/glibc-${GLIBC_VER}.apk > /tmp/glibc-${GLIBC_VER}.apk \
#    && apk add --no-cache /tmp/glibc-${GLIBC_VER}.apk \
#    && curl -LfsS ${ALPINE_GLIBC_REPO}/${GLIBC_VER}/glibc-bin-${GLIBC_VER}.apk > /tmp/glibc-bin-${GLIBC_VER}.apk \
#    && apk add --no-cache /tmp/glibc-bin-${GLIBC_VER}.apk \
#    && curl -Ls ${ALPINE_GLIBC_REPO}/${GLIBC_VER}/glibc-i18n-${GLIBC_VER}.apk > /tmp/glibc-i18n-${GLIBC_VER}.apk \
#    && apk add --no-cache /tmp/glibc-i18n-${GLIBC_VER}.apk \
#    && /usr/glibc-compat/bin/localedef --force --inputfile POSIX --charmap UTF-8 "$LANG" || true \
#    && echo "export LANG=$LANG" > /etc/profile.d/locale.sh \
#    && curl -LfsS ${GCC_LIBS_URL} -o /tmp/gcc-libs.tar.zst \
#    && echo "${GCC_LIBS_SHA256} */tmp/gcc-libs.tar.zst" | sha256sum -c - \
#    && mkdir /tmp/gcc \
#    && zstd -d /tmp/gcc-libs.tar.zst --output-dir-flat /tmp \
#    && tar -xf /tmp/gcc-libs.tar -C /tmp/gcc \
#    && mv /tmp/gcc/usr/lib/libgcc* /tmp/gcc/usr/lib/libstdc++* /usr/glibc-compat/lib \
#    && strip /usr/glibc-compat/lib/libgcc_s.so.* /usr/glibc-compat/lib/libstdc++.so* \
#    && curl -LfsS ${ZLIB_URL} -o /tmp/libz.tar.xz \
#    && echo "${ZLIB_SHA256} */tmp/libz.tar.xz" | sha256sum -c - \
#    && mkdir /tmp/libz \
#    && tar -xf /tmp/libz.tar.xz -C /tmp/libz \
#    && mv /tmp/libz/usr/lib/libz.so* /usr/glibc-compat/lib \
#    && apk del --purge .build-deps glibc-i18n \
#    && rm -rf /tmp/*.apk /tmp/gcc /tmp/gcc-libs.tar* /tmp/libz /tmp/libz.tar.xz /var/cache/apk/*
#
#VOLUME /web
#EXPOSE 5050
#WORKDIR /app
#COPY --from=build /src/core/target/modules/plugin-1.0-SNAPSHOT.jar ./modules/plugin.jar
#COPY --from=build /opt/jdk-mini ./jre
#ENTRYPOINT [ "/app/jre/bin/java", "--module-path", "/app/jre/:/app/modules", "-m", "core/x.snowroller.ServerExample"]