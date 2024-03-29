# Описание страниц 

Описание элементов интерфейса веб-приложения, действий, которые они выполняют, и 
данных, с ними связанных.

### 1. Главная страница

_Элементы интерфейса и действия:_
 * Кнопка "Управление клиентами" для получения списка клиентов оператора.
 * Кнопка "Управление услугами" для получения списка предоставляемых 
оператором услуг.

_Данные:_ нет.

### 2. Список клиентов

_Элементы интерфейса и действия:_
 * Таблица с информацией о клиентах (и о физ., и о юр. лицах).
    * Кликабельные заголовки, позволяющие сортировать таблицу.
    * Кликабельные строки таблицы, позволяющие перейти к странице клиента.
 * Поле ввода для текстового поиска по владельцу (ФИО или наименование организации).
 * Флажки и поля ввода для фильтрации по различным параметрам (например, 
по кол-ву счетов, по их балансу, статусу).
* Кнопка "Заключить новый договор" для добавления нового клиента.
* Поле ввода, принимающее номер счёта, для получения информации о счёте клиента.

_Данные:_
 * Таблица с информацией о клиентах имеет поля: физ. или юр. лицо, владелец 
(ФИО или наименование организации), идентификатор (паспорт или ОГРН), 
регион заключения договора, контактный телефон, контактный e-mail, 
список номеров счетов, время заключения первого договора.

### 3. Страница заключения договора

_Элементы интерфейса и действия:_
 * Radio button для выбора физ. или юр. лица.
 * Форма для ввода данных нового клиента.
 * Кнопка "Сохранить" для внесения клиента в базу данных.

_Данные:_
 * Форма имеет поля ввода для всех полей отношений "Физическое лицо" или 
"Юридическое лицо" в зависимости от выбора radio button, кроме поля "Номер клиента".

### 4. Информация о клиенте

_Элементы интерфейса и действия:_
 * Детальная информация об отдельном клиенте в виде текстовых полей. 
При нажатии на кнопку редактирования поля становятся активными, позволяя
править данные.
 * Кнопка "Сохранить", которая становится активной в режиме редактирования.
 * Список счетов клиента. Кликабельные элементы для перехода к странице счёта.
 * Кнопка для открытия нового счёта клиента.

_Данные:_
 * Текстовые поля представляют следующие данные: владелец
   (ФИО или наименование организации), идентификатор (паспорт или ОГРН),
   регион заключения договора, контактный телефон, контактный e-mail,
   ФИО контактного лица и адрес организации для юр. лиц,
   время заключения первого договора.
 * Список счетов имеет поля: номер счёта, статус (активен, 
заблокирован, закрыт), баланс.

### 5. Счёт

_Элементы интерфейса и действия:_
 * Данные по счёту в виде неизменяемого списка.
 * Список в данный момент подключенных к счёту услуг. Кликабельные элементы
для перехода к странице услуги.
 * Кнопка "Операции по счёту"
 * Кнопка для подключения существующей услуги.
 * Кнопки для блокировки и закрытия счёта.

_Данные:_
 * Список данных по счёту: ФИО владельца или наименование организации, 
баланс, статус, дата и время открытия,
обслуживаемый номер телефона, максимально возможная задолженность и
оставшееся время для её погашения (если задолженность имеется).
 * Список подключенных к счёту услуг имеет поля: 
 название услуги, тип абонентской платы, абонентская плата (если имеется),
 дата и время окончания действия услуги (если имеется).

### 6. Список предоставляемых услуг

_Элементы интерфейса и действия:_
 * Таблица с краткой информацией обо всех услугах, предоставляемых оператором.
   * Кликабельные строки, позволяющие перейти к странице услуги.
   * Кликабельные заголовки для сортировки.
 * Флажки и поля ввода для фильтрации по различным параметрам. Поле ввода для
текстового поиска по названию и описанию услуг.
 * Кнопка "Новая услуга" для перехода к странице создания новой услуги.

_Данные:_
* Таблица с информацией об услугах: название услуги, тип абонентской платы, 
её стоимость (если имеется).


### 7. Список подключаемых услуг

Имеет такую же структуру и данные, как и п.6 "Список предоставляемых услуг",
за исключением следующих особенностей. 
* Первым полем таблицы услуг является 
check box (для уже подключенных услуг он находится в отмеченном состоянии).
* Добавляется два поля - стоимость подключения и стоимость отключения услуги.
* Добавляется кнопка "Сохранить", которая изменяет подключенные к счёту услуги
в соответствии с отмеченными check boxes в таблице.

### 8. Информация об услуге

_Элементы интерфейса и действия:_
 * Детальная информация об услуге в виде текстовых полей.
При нажатии на кнопку редактирования поля становятся активными, позволяя 
править данные.
 * Кнопка "Сохранить", которая становится активной в режиме редактирования.
 * Кнопка для удаления услуги.

_Данные:_
 * Данные об услуге: название услуги, тип абонентской платы, подробный тарифный
план (стоимость активации, абонентской платы и отключения), предоставляемые пакеты
(связь, Интернет, СМС), продолжительность действия после подключения,
подробное текстовое описание.

### 9. Добавление услуги

_Элементы интерфейса и действия:_
 * Форма для создания новой услуги.
 * Кнопка "Сохранить" для внесения новой услуги в базу данных.

_Данные:_
 * Форма имеет поля, позволяющие заполнить данные полей отношения "Услуга" (см. п.8);
в частности, имеется drop box для выбора значений типа enum.

### 10. Операции по счёту

_Элементы интерфейса и действия:_
 * Номер счёта.
 * Список всех операций.
 * Элементы для выбора интервала времени. Они задают фильтр для списка операций
и для отрисовывания линейчатой диаграммы суммарных пополнений и расходов.

_Данные:_
 * Список операций имеет поля: дата и время исполнения, сумма, тип операции,
   название связанной услуги (последнее кликабельно для перехода к странице услуги).
 * Линейчатая диаграмма: показывает сумму произведенных операций, разделенную
по типам операций (пополнение, списания за подключение, за абонентскую плату
и за отключение услуг) в заданном временном интервале.