INSERT INTO department (code, name) VALUES
                                        ('DEP-PFR', 'Ведомство ПФР'),
                                        ('DEP-FNS', 'Ведомство ФНС');
INSERT INTO application_type (code, name) VALUES
                                        ('DEP-PFR', 'Заявление в ПФР'),
                                        ('DEP-FNS', 'Заявление в ФНС');
INSERT INTO application_status (code, name) VALUES
                                              ('NEW', 'Заявление зарегистрировано'),
                                              ('PROCESSING', 'Заявление было передано в ведомство'),
                                              ('COMPLETED', 'Заявление было обработано ведомством');
