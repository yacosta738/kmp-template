# Description: Makefile for KMP Template project
# Author: Yuniel Acosta <https://twitter.com/yacosta738>

MAKEFILES += $(shell find . -name Makefile)

.PHONY: all
all: check build

.PHONY: check
check:
	@./gradlew clean check sonar aggregateReports

.PHONY: build
build:
	@./gradlew build --warning-mode all
.PHONY: test
test:
	@./gradlew test
