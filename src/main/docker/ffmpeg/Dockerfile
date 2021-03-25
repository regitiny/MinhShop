FROM ubuntu:20.04
ENV TIME_ZONE 'Asia/Ho_Chi_Minh'
RUN echo $TIME_ZONE > /etc/timezone && \
    apt-get update && apt-get install tzdata -y && \
    rm /etc/localtime && \
    ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean
RUN apt-get install ffmpeg -y
RUN ffmpeg -version && echo 'Done!'
#ADD
#ENTRYPOINT ["connectRedisCluster"]
