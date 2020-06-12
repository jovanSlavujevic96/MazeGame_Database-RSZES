QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

CONFIG += c++17

# The following define makes your compiler emit warnings if you use
# any Qt feature that has been marked deprecated (the exact warnings
# depend on your compiler). Please consult the documentation of the
# deprecated API in order to know how to port your code away from it.
DEFINES += QT_DEPRECATED_WARNINGS

# You can also make your code fail to compile if it uses deprecated APIs.
# In order to do so, uncomment the following line.
# You can also select to disable deprecated APIs only up to a certain version of Qt.
#DEFINES += QT_DISABLE_DEPRECATED_BEFORE=0x060000    # disables all the APIs deprecated before Qt 6.0.0

SOURCES += \
    main.cpp \
    leaderboard.cpp \
    widget.cpp

HEADERS += \
    leaderboard.h \
    widget.h

FORMS += \
    leaderboard.ui \
    widget.ui

INCLUDEPATH += -I "../3rdparty"

INCLUDEPATH += -I "/usr/include/mysql/"
INCLUDEPATH += -I "/usr/include/mysql/mysql/"

DEPENDPATH += . "../3rdparty/server" "../3rdparty/sql"

LIBS += -L../3rdparty/server/lib -lserver
LIBS += -L../3rdparty/sql/lib -lsql
LIBS += -L`mysql_config --cflags --libs`
LIBS += -pthread

# Default rules for deployment.
qnx: target.path = /tmp/$${TARGET}/bin
else: unix:!android: target.path = /opt/$${TARGET}/bin
!isEmpty(target.path): INSTALLS += target