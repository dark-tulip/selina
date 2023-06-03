FROM python:3.10

# take all the files in the working directory, include the folders and sub-folders
COPY . /project

# working directory, where to execute commands
WORKDIR project

# expose internal port
EXPOSE 8080

# install python packages required for execution of ml service
RUN pip install --no-cache-dir --upgrade pip && pip install --no-cache-dir -r requirements.txt

# run when container executed (started)
ENTRYPOINT ["python", "server.py"]

# docker build . -t python-ml-service # to create image
# docker run -it -p 8080:8081 --name service3 python-ml-service
# docker exec -it 10f33e7c0443 /bin/bash
