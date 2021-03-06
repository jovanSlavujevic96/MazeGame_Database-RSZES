 #ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>

QT_BEGIN_NAMESPACE
namespace Ui
{
  class Widget;
};
QT_END_NAMESPACE

class SQL;

class Widget : public QWidget
{
  Q_OBJECT

public:
  explicit Widget(QWidget *parent = nullptr);
  virtual ~Widget();

  void assign(SQL* sql);

private slots:
  void on_pushButton_clicked();
  void on_lineEdit_2_returnPressed();
  void on_lineEdit_returnPressed();

private:
  Ui::Widget *ui;
  SQL* sql;

  void SQL_admin_login();
};
#endif // WIDGET_H
