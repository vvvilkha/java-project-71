.PHONY: build test lint run-dist

build:
	cd app && ./gradlew build

test:
	cd app && ./gradlew test

lint:
	cd app && ./gradlew checkstyleMain checkstyleTest

run-dist:
	@./app/build/install/app/bin/app