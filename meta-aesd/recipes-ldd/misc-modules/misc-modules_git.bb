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
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://git@github.com/AnishNandhan/aesd-assignment-7-AnishNandhan.git;protocol=ssh;branch=main \
           file://0001-include-only-scull-and-misc-modules.patch \
           file://0002-change-kernel-source-dir-variable-name.patch \
           file://module-load-unload \
           "

PV = "1.0+git${SRCPV}"
SRCREV = "72d6b180b4608c8f6584882e59e4d04f2b1e80a4"

S = "${WORKDIR}/git"

RPROVIDES:${PN} += "kernel-module-hello kernel-module-faulty"
FILES:${PN} += "${sysconfdir}/init.d/module-load-unload"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
# Unable to find means of passing kernel path into install makefile - if kernel path is hardcoded you will need to patch the makefile. Note that the variable KERNEL_SRC will be passed in as the kernel source path.

inherit update-rc.d
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "module-load-unload" 

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/module-load-unload ${D}${sysconfdir}/init.d
}