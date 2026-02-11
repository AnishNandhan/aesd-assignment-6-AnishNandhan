# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/AnishNandhan/aesd-assignments-3-and-later-AnishNandhan.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "166032aefe6b8bcbfbaaee36410db30e44ecc035"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket ${sysconfdir}/init.d/aesdsocket-start-stop"

TARGET_LDFLAGS += "-lpthread -lrt"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop" 

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/aesdsocket ${D}${bindir}

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d 
}
