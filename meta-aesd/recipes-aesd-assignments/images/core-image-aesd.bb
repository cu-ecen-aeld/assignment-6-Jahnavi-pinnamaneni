inherit core-image
IMAGE_INSTALL_append = " aesd-assignments"
IMAGE_INSTALL_append = " scull"
CORE_IMAGE_EXTRA_INSTALL += " openssh"
inherit extrausers
EXTRA_USERS_PARAMS = "usermod -P root root;"
