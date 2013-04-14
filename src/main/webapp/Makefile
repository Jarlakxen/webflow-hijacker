BOOTSTRAP_LESS = ./less/bootstrap/bootstrap.less
BOOTSTRAP_RESPONSIVE_LESS = ./less/bootstrap/responsive.less
DATE=$(shell date +%I:%M%p)
CHECK=\033[32m✔\033[39m
HR=\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#\#



#
# CLEANS THE ROOT DIRECTORY OF PRIOR BUILDS
#

clean:
	rm -f js/bootstrap/bootstrap.min.js
	rm -rf css/bootstrap/

#
# BUILD SIMPLE BOOTSTRAP DIRECTORY
# recess & uglifyjs are required
#

bootstrap: bootstrap-css bootstrap-js


#
# JS COMPILE
#
bootstrap-js:
	cat js/bootstrap/bootstrap-transition.js js/bootstrap/bootstrap-alert.js js/bootstrap/bootstrap-button.js js/bootstrap/bootstrap-carousel.js js/bootstrap/bootstrap-collapse.js js/bootstrap/bootstrap-dropdown.js js/bootstrap/bootstrap-modal.js js/bootstrap/bootstrap-tooltip.js js/bootstrap/bootstrap-popover.js js/bootstrap/bootstrap-scrollspy.js js/bootstrap/bootstrap-tab.js js/bootstrap/bootstrap-typeahead.js js/bootstrap/bootstrap-affix.js > js/bootstrap/bootstrap.js
	uglifyjs -nc js/bootstrap/bootstrap.js > js/bootstrap/bootstrap.min.tmp.js
	echo "/*!\n* Bootstrap.js by @fat & @mdo\n* Copyright 2012 Twitter, Inc.\n* http://www.apache.org/licenses/LICENSE-2.0.txt\n*/" > js/bootstrap/copyright.js
	cat js/bootstrap/copyright.js js/bootstrap/bootstrap.min.tmp.js > js/bootstrap/bootstrap.min.js
	rm js/bootstrap/copyright.js js/bootstrap/bootstrap.min.tmp.js

#
# CSS COMPLILE
#

bootstrap-css:
	mkdir -p ./css/bootstrap
	recess --compile ${BOOTSTRAP_LESS} > ./css/bootstrap/bootstrap.css
	recess --compress ${BOOTSTRAP_LESS} > ./css/bootstrap/bootstrap.min.css
	recess --compile ${BOOTSTRAP_RESPONSIVE_LESS} > ./css/bootstrap/bootstrap-responsive.css
	recess --compress ${BOOTSTRAP_RESPONSIVE_LESS} > ./css/bootstrap/bootstrap-responsive.min.css


#
# WATCH LESS FILES
#

watch:
	echo "Watching less files..."; \
	watchr -e "watch('less/bootstrap/.*\.less') { system 'make' }"


.PHONY: watch bootstrap-css bootstrap-js