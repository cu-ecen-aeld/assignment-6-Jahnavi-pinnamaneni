# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
#LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-Jahnavi-pinnamaneni;protocol=ssh;branch=master \
file://init-script.sh"
# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "c879a6961398550d1f840df040ac85b3ac4e72b7"

S = "${WORKDIR}/git"

FILES_${PN} += "${bindir}/scull"
FILES_${PN} += "${bindir}/scull_load"
FILES_${PN} += "${bindir}/scull_unload"
FILES_${PN} += "${sysconfdir}/init.d/init-script.sh"
INSANE_SKIP_${PN} = "installed-vs-shipped"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

inherit module
inherit update-rc.d
INITSCRIPT_PACKAGES="${PN}"
INITSCRIPT_NAME_${PN}="init-script.sh"

EXTRA_OEMAKE_append_task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install () {
	# TODO: Install your binaries/scripts here.
	# Be sure to install the target directory with install -d first
	# Yocto variables ${D} and ${S} are useful here, which you can read about at 
	# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-D
	# and
	# https://www.yoctoproject.org/docs/latest/ref-manual/ref-manual.html#var-S
	# See example at https://github.com/cu-ecen-aeld/ecen5013-yocto/blob/ecen5013-hello-world/meta-ecen5013/recipes-ecen5013/ecen5013-hello-world/ecen5013-hello-world_git.bb
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${bindir}
	install -m 0755 ${S}/scull/scull_load ${D}${bindir}/
	install -m 0755 ${S}/scull/scull_unload ${D}${bindir}/	
	install -m 0755 ${WORKDIR}/init-script.sh ${D}${sysconfdir}/init.d
}
