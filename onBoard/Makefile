
all: run

setup:
	make setup -C server_streaming
	./SETUP.sh

run:
	make run -C server_streaming &
	make run -C server &
	
