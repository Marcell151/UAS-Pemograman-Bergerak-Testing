package com.example.kantinkampus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "kantinkampus.db";
    private static final int DATABASE_VERSION = 4; // Updated version

    // Table Stand
    private static final String TABLE_STAND = "table_stand";
    private static final String STAND_ID = "id";
    private static final String STAND_NAMA = "nama";
    private static final String STAND_DESKRIPSI = "deskripsi";

    // Table Users
    private static final String TABLE_USERS = "table_users";
    private static final String USER_ID = "id";
    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE = "phone";
    private static final String USER_ROLE = "role";
    private static final String USER_CREATED_AT = "created_at";

    // Table Menu (Enhanced)
    private static final String TABLE_MENU = "table_menu";
    private static final String MENU_ID = "id";
    private static final String MENU_STAND_ID = "stand_id";
    private static final String MENU_NAMA = "nama";
    private static final String MENU_HARGA = "harga";
    private static final String MENU_DESKRIPSI = "deskripsi";
    private static final String MENU_KATEGORI = "kategori";
    private static final String MENU_IMAGE = "image";
    private static final String MENU_AVAILABLE = "available";

    // Table Cart
    private static final String TABLE_CART = "table_cart";
    private static final String CART_ID = "id";
    private static final String CART_MENU_ID = "menu_id";
    private static final String CART_QTY = "qty";

    // Table History
    private static final String TABLE_HISTORY = "table_history";
    private static final String HISTORY_ID = "id";
    private static final String HISTORY_ITEMS = "items";
    private static final String HISTORY_TOTAL = "total";
    private static final String HISTORY_TANGGAL = "tanggal";

    // Table Orders
    private static final String TABLE_ORDERS = "table_orders";
    private static final String ORDER_ID = "id";
    private static final String ORDER_USER_NAME = "user_name";
    private static final String ORDER_STAND_NAME = "stand_name";
    private static final String ORDER_ITEMS = "items";
    private static final String ORDER_TOTAL = "total";
    private static final String ORDER_STATUS = "status";
    private static final String ORDER_CREATED_AT = "created_at";
    private static final String ORDER_PAYMENT_METHOD = "payment_method";
    private static final String ORDER_NOTES = "notes";

    // Table Order Items
    private static final String TABLE_ORDER_ITEMS = "table_order_items";
    private static final String ORDER_ITEM_ID = "id";
    private static final String ORDER_ITEM_ORDER_ID = "order_id";
    private static final String ORDER_ITEM_MENU_ID = "menu_id";
    private static final String ORDER_ITEM_MENU_NAME = "menu_name";
    private static final String ORDER_ITEM_QTY = "qty";
    private static final String ORDER_ITEM_PRICE = "price";

    // Table Reviews
    private static final String TABLE_REVIEWS = "table_reviews";
    private static final String REVIEW_ID = "id";
    private static final String REVIEW_MENU_ID = "menu_id";
    private static final String REVIEW_USER_NAME = "user_name";
    private static final String REVIEW_RATING = "rating";
    private static final String REVIEW_COMMENT = "comment";
    private static final String REVIEW_CREATED_AT = "created_at";

    // Table Favorites
    private static final String TABLE_FAVORITES = "table_favorites";
    private static final String FAVORITE_ID = "id";
    private static final String FAVORITE_MENU_ID = "menu_id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Stand Table
        String createStandTable = "CREATE TABLE " + TABLE_STAND + " ("
                + STAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + STAND_NAMA + " TEXT, "
                + STAND_DESKRIPSI + " TEXT)";
        db.execSQL(createStandTable);

        // Create Users Table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME + " TEXT UNIQUE, "
                + USER_PASSWORD + " TEXT, "
                + USER_NAME + " TEXT, "
                + USER_EMAIL + " TEXT, "
                + USER_PHONE + " TEXT, "
                + USER_ROLE + " TEXT, "
                + USER_CREATED_AT + " TEXT)";
        db.execSQL(createUsersTable);

        // Create Menu Table (Enhanced)
        String createMenuTable = "CREATE TABLE " + TABLE_MENU + " ("
                + MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MENU_STAND_ID + " INTEGER, "
                + MENU_NAMA + " TEXT, "
                + MENU_HARGA + " INTEGER, "
                + MENU_DESKRIPSI + " TEXT, "
                + MENU_KATEGORI + " TEXT, "
                + MENU_IMAGE + " TEXT, "
                + MENU_AVAILABLE + " INTEGER DEFAULT 1)";
        db.execSQL(createMenuTable);

        // Create Cart Table
        String createCartTable = "CREATE TABLE " + TABLE_CART + " ("
                + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_MENU_ID + " INTEGER, "
                + CART_QTY + " INTEGER)";
        db.execSQL(createCartTable);

        // Create History Table
        String createHistoryTable = "CREATE TABLE " + TABLE_HISTORY + " ("
                + HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HISTORY_ITEMS + " TEXT, "
                + HISTORY_TOTAL + " INTEGER, "
                + HISTORY_TANGGAL + " TEXT)";
        db.execSQL(createHistoryTable);

        // Create Orders Table
        String createOrdersTable = "CREATE TABLE " + TABLE_ORDERS + " ("
                + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ORDER_USER_NAME + " TEXT, "
                + ORDER_STAND_NAME + " TEXT, "
                + ORDER_ITEMS + " TEXT, "
                + ORDER_TOTAL + " INTEGER, "
                + ORDER_STATUS + " TEXT, "
                + ORDER_CREATED_AT + " TEXT, "
                + ORDER_PAYMENT_METHOD + " TEXT, "
                + ORDER_NOTES + " TEXT)";
        db.execSQL(createOrdersTable);

        // Create Order Items Table
        String createOrderItemsTable = "CREATE TABLE " + TABLE_ORDER_ITEMS + " ("
                + ORDER_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ORDER_ITEM_ORDER_ID + " INTEGER, "
                + ORDER_ITEM_MENU_ID + " INTEGER, "
                + ORDER_ITEM_MENU_NAME + " TEXT, "
                + ORDER_ITEM_QTY + " INTEGER, "
                + ORDER_ITEM_PRICE + " INTEGER)";
        db.execSQL(createOrderItemsTable);

        // Create Reviews Table
        String createReviewsTable = "CREATE TABLE " + TABLE_REVIEWS + " ("
                + REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REVIEW_MENU_ID + " INTEGER, "
                + REVIEW_USER_NAME + " TEXT, "
                + REVIEW_RATING + " INTEGER, "
                + REVIEW_COMMENT + " TEXT, "
                + REVIEW_CREATED_AT + " TEXT)";
        db.execSQL(createReviewsTable);

        // Create Favorites Table
        String createFavoritesTable = "CREATE TABLE " + TABLE_FAVORITES + " ("
                + FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FAVORITE_MENU_ID + " INTEGER)";
        db.execSQL(createFavoritesTable);

        // Insert Initial Data
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            try {
                db.execSQL("ALTER TABLE " + TABLE_MENU + " ADD COLUMN " + MENU_DESKRIPSI + " TEXT");
            } catch (Exception e) { }
            try {
                db.execSQL("ALTER TABLE " + TABLE_MENU + " ADD COLUMN " + MENU_KATEGORI + " TEXT");
            } catch (Exception e) { }
            try {
                db.execSQL("ALTER TABLE " + TABLE_MENU + " ADD COLUMN " + MENU_IMAGE + " TEXT");
            } catch (Exception e) { }
            try {
                db.execSQL("ALTER TABLE " + TABLE_MENU + " ADD COLUMN " + MENU_AVAILABLE + " INTEGER DEFAULT 1");
            } catch (Exception e) { }
        }

        if (oldVersion < 3) {
            String createOrdersTable = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS + " ("
                    + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ORDER_USER_NAME + " TEXT, "
                    + ORDER_STAND_NAME + " TEXT, "
                    + ORDER_ITEMS + " TEXT, "
                    + ORDER_TOTAL + " INTEGER, "
                    + ORDER_STATUS + " TEXT, "
                    + ORDER_CREATED_AT + " TEXT, "
                    + ORDER_PAYMENT_METHOD + " TEXT, "
                    + ORDER_NOTES + " TEXT)";
            db.execSQL(createOrdersTable);

            String createOrderItemsTable = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDER_ITEMS + " ("
                    + ORDER_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ORDER_ITEM_ORDER_ID + " INTEGER, "
                    + ORDER_ITEM_MENU_ID + " INTEGER, "
                    + ORDER_ITEM_MENU_NAME + " TEXT, "
                    + ORDER_ITEM_QTY + " INTEGER, "
                    + ORDER_ITEM_PRICE + " INTEGER)";
            db.execSQL(createOrderItemsTable);

            String createReviewsTable = "CREATE TABLE IF NOT EXISTS " + TABLE_REVIEWS + " ("
                    + REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + REVIEW_MENU_ID + " INTEGER, "
                    + REVIEW_USER_NAME + " TEXT, "
                    + REVIEW_RATING + " INTEGER, "
                    + REVIEW_COMMENT + " TEXT, "
                    + REVIEW_CREATED_AT + " TEXT)";
            db.execSQL(createReviewsTable);

            String createFavoritesTable = "CREATE TABLE IF NOT EXISTS " + TABLE_FAVORITES + " ("
                    + FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FAVORITE_MENU_ID + " INTEGER)";
            db.execSQL(createFavoritesTable);
        }

        if (oldVersion < 4) {
            // Create Users Table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                    + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_USERNAME + " TEXT UNIQUE, "
                    + USER_PASSWORD + " TEXT, "
                    + USER_NAME + " TEXT, "
                    + USER_EMAIL + " TEXT, "
                    + USER_PHONE + " TEXT, "
                    + USER_ROLE + " TEXT, "
                    + USER_CREATED_AT + " TEXT)";
            db.execSQL(createUsersTable);

            // Insert default users
            insertDefaultUsers(db);
        }
    }

    private void insertInitialData(SQLiteDatabase db) {
        // Insert Stands
        ContentValues stand1 = new ContentValues();
        stand1.put(STAND_NAMA, "Warung Nasi Bu Sari");
        stand1.put(STAND_DESKRIPSI, "Makanan Berat");
        db.insert(TABLE_STAND, null, stand1);

        ContentValues stand2 = new ContentValues();
        stand2.put(STAND_NAMA, "Minuman Fresh Pak Danu");
        stand2.put(STAND_DESKRIPSI, "Minuman");
        db.insert(TABLE_STAND, null, stand2);

        ContentValues stand3 = new ContentValues();
        stand3.put(STAND_NAMA, "Snack Corner Mbak Lia");
        stand3.put(STAND_DESKRIPSI, "Snack");
        db.insert(TABLE_STAND, null, stand3);

        // Insert Menu Stand 1
        insertMenu(db, 1, "Nasi Ayam Geprek", 18000, "Nasi putih dengan ayam geprek pedas dan sambal", "Makanan Berat", null);
        insertMenu(db, 1, "Nasi Telur Balado", 12000, "Nasi dengan telur balado pedas", "Makanan Berat", null);
        insertMenu(db, 1, "Nasi Ayam Kremes", 17000, "Nasi dengan ayam kremes renyah", "Makanan Berat", null);
        insertMenu(db, 1, "Nasi Ayam Bakar", 20000, "Nasi dengan ayam bakar bumbu kecap", "Makanan Berat", null);
        insertMenu(db, 1, "Nasi Lele Goreng", 16000, "Nasi dengan lele goreng crispy", "Makanan Berat", null);

        // Insert Menu Stand 2
        insertMenu(db, 2, "Es Teh Manis", 5000, "Teh manis dingin segar", "Minuman", null);
        insertMenu(db, 2, "Es Jeruk Fresh", 7000, "Jus jeruk segar tanpa gula", "Minuman", null);
        insertMenu(db, 2, "Lemon Tea", 8000, "Teh lemon segar", "Minuman", null);
        insertMenu(db, 2, "Matcha Latte", 12000, "Minuman matcha creamy", "Minuman", null);
        insertMenu(db, 2, "Thai Tea", 10000, "Thai tea original", "Minuman", null);

        // Insert Menu Stand 3
        insertMenu(db, 3, "Kentang Goreng", 10000, "Kentang goreng crispy dengan saus", "Snack", null);
        insertMenu(db, 3, "Sosis Bakar", 12000, "Sosis bakar jumbo", "Snack", null);
        insertMenu(db, 3, "Nugget Goreng", 13000, "Nugget ayam goreng crispy", "Snack", null);
        insertMenu(db, 3, "Roti Bakar Coklat", 15000, "Roti bakar dengan selai coklat", "Snack", null);
        insertMenu(db, 3, "Pisang Coklat", 14000, "Pisang goreng dengan topping coklat", "Snack", null);

        // Insert default users
        insertDefaultUsers(db);
    }

    private void insertDefaultUsers(SQLiteDatabase db) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        // Insert admin user
        ContentValues admin = new ContentValues();
        admin.put(USER_USERNAME, "admin");
        admin.put(USER_PASSWORD, "admin123");
        admin.put(USER_NAME, "Administrator");
        admin.put(USER_EMAIL, "admin@kantinkampus.com");
        admin.put(USER_PHONE, "081234567890");
        admin.put(USER_ROLE, User.ROLE_ADMIN);
        admin.put(USER_CREATED_AT, currentDate);
        db.insert(TABLE_USERS, null, admin);

        // Insert customer user
        ContentValues customer = new ContentValues();
        customer.put(USER_USERNAME, "user");
        customer.put(USER_PASSWORD, "user123");
        customer.put(USER_NAME, "User Customer");
        customer.put(USER_EMAIL, "user@example.com");
        customer.put(USER_PHONE, "081234567891");
        customer.put(USER_ROLE, User.ROLE_CUSTOMER);
        customer.put(USER_CREATED_AT, currentDate);
        db.insert(TABLE_USERS, null, customer);
    }

    private void insertMenu(SQLiteDatabase db, int standId, String nama, int harga, String deskripsi, String kategori, String image) {
        ContentValues values = new ContentValues();
        values.put(MENU_STAND_ID, standId);
        values.put(MENU_NAMA, nama);
        values.put(MENU_HARGA, harga);
        values.put(MENU_DESKRIPSI, deskripsi);
        values.put(MENU_KATEGORI, kategori);
        values.put(MENU_IMAGE, image);
        values.put(MENU_AVAILABLE, 1);
        db.insert(TABLE_MENU, null, values);
    }

    // ==================== STAND METHODS ====================
    public List<Stand> getAllStands() {
        List<Stand> stands = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STAND, null);

        if (cursor.moveToFirst()) {
            do {
                Stand stand = new Stand();
                stand.setId(cursor.getInt(0));
                stand.setNama(cursor.getString(1));
                stand.setDeskripsi(cursor.getString(2));
                stands.add(stand);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return stands;
    }

    // ==================== MENU METHODS ====================
    public List<Menu> getMenuByStandId(int standId) {
        List<Menu> menus = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU + " WHERE " + MENU_STAND_ID + " = ?",
                new String[]{String.valueOf(standId)});

        if (cursor.moveToFirst()) {
            do {
                Menu menu = createMenuFromCursor(cursor);
                menus.add(menu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return menus;
    }

    public Menu getMenuById(int menuId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU + " WHERE " + MENU_ID + " = ?",
                new String[]{String.valueOf(menuId)});

        Menu menu = null;
        if (cursor.moveToFirst()) {
            menu = createMenuFromCursor(cursor);
        }
        cursor.close();
        return menu;
    }

    private Menu createMenuFromCursor(Cursor cursor) {
        Menu menu = new Menu();
        menu.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MENU_ID)));
        menu.setStandId(cursor.getInt(cursor.getColumnIndexOrThrow(MENU_STAND_ID)));
        menu.setNama(cursor.getString(cursor.getColumnIndexOrThrow(MENU_NAMA)));
        menu.setHarga(cursor.getInt(cursor.getColumnIndexOrThrow(MENU_HARGA)));

        int deskripsiIndex = cursor.getColumnIndex(MENU_DESKRIPSI);
        if (deskripsiIndex != -1 && !cursor.isNull(deskripsiIndex)) {
            menu.setDeskripsi(cursor.getString(deskripsiIndex));
        }

        int kategoriIndex = cursor.getColumnIndex(MENU_KATEGORI);
        if (kategoriIndex != -1 && !cursor.isNull(kategoriIndex)) {
            menu.setKategori(cursor.getString(kategoriIndex));
        }

        int imageIndex = cursor.getColumnIndex(MENU_IMAGE);
        if (imageIndex != -1 && !cursor.isNull(imageIndex)) {
            menu.setImage(cursor.getString(imageIndex));
        }

        int availableIndex = cursor.getColumnIndex(MENU_AVAILABLE);
        if (availableIndex != -1) {
            menu.setAvailable(cursor.getInt(availableIndex) == 1);
        } else {
            menu.setAvailable(true);
        }

        calculateMenuRating(menu);
        return menu;
    }

    private void calculateMenuRating(Menu menu) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT AVG(" + REVIEW_RATING + ") as avg_rating, COUNT(*) as total " +
                        "FROM " + TABLE_REVIEWS + " WHERE " + REVIEW_MENU_ID + " = ?",
                new String[]{String.valueOf(menu.getId())});

        if (cursor.moveToFirst()) {
            double avgRating = cursor.getDouble(0);
            int total = cursor.getInt(1);
            menu.setAverageRating(avgRating);
            menu.setTotalReviews(total);
        }
        cursor.close();
    }

    public void addMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MENU_STAND_ID, menu.getStandId());
        values.put(MENU_NAMA, menu.getNama());
        values.put(MENU_HARGA, menu.getHarga());
        values.put(MENU_DESKRIPSI, menu.getDeskripsi());
        values.put(MENU_KATEGORI, menu.getKategori());
        values.put(MENU_IMAGE, menu.getImage());
        values.put(MENU_AVAILABLE, menu.isAvailable() ? 1 : 0);
        db.insert(TABLE_MENU, null, values);
    }

    public void updateMenu(Menu menu) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MENU_NAMA, menu.getNama());
        values.put(MENU_HARGA, menu.getHarga());
        values.put(MENU_DESKRIPSI, menu.getDeskripsi());
        values.put(MENU_KATEGORI, menu.getKategori());
        values.put(MENU_IMAGE, menu.getImage());
        values.put(MENU_AVAILABLE, menu.isAvailable() ? 1 : 0);
        db.update(TABLE_MENU, values, MENU_ID + " = ?", new String[]{String.valueOf(menu.getId())});
    }

    public void deleteMenu(int menuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MENU, MENU_ID + " = ?", new String[]{String.valueOf(menuId)});
    }

    public List<Menu> getAllMenus() {
        List<Menu> menus = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);

        if (cursor.moveToFirst()) {
            do {
                Menu menu = createMenuFromCursor(cursor);
                menus.add(menu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return menus;
    }

    // ==================== CART METHODS ====================
    public void addToCart(int userId, int menuId, int qty, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " + CART_MENU_ID + " = ?",
                new String[]{String.valueOf(menuId)});

        if (cursor.moveToFirst()) {
            int currentQty = cursor.getInt(2);
            ContentValues values = new ContentValues();
            values.put(CART_QTY, currentQty + qty);
            db.update(TABLE_CART, values, CART_MENU_ID + " = ?", new String[]{String.valueOf(menuId)});
        } else {
            ContentValues values = new ContentValues();
            values.put(CART_MENU_ID, menuId);
            values.put(CART_QTY, qty);
            db.insert(TABLE_CART, null, values);
        }
        cursor.close();
    }

    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART, null);

        if (cursor.moveToFirst()) {
            do {
                CartItem item = new CartItem();
                item.setId(cursor.getInt(0));
                int menuId = cursor.getInt(1);
                item.setMenu(getMenuById(menuId));
                item.setQty(cursor.getInt(2));
                cartItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartItems;
    }

    public List<CartItem> getCartItems(int userId) {
        // For backward compatibility, just call getCartItems()
        // In future, can filter by userId if needed
        return getCartItems();
    }

    public void updateCartQty(int cartId, int qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (qty <= 0) {
            db.delete(TABLE_CART, CART_ID + " = ?", new String[]{String.valueOf(cartId)});
        } else {
            ContentValues values = new ContentValues();
            values.put(CART_QTY, qty);
            db.update(TABLE_CART, values, CART_ID + " = ?", new String[]{String.valueOf(cartId)});
        }
    }

    public void clearCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, null, null);
    }

    public int getCartCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + CART_QTY + ") FROM " + TABLE_CART, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    // ==================== HISTORY METHODS ====================
    public void addToHistory(String items, int total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HISTORY_ITEMS, items);
        values.put(HISTORY_TOTAL, total);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        values.put(HISTORY_TANGGAL, sdf.format(new Date()));
        db.insert(TABLE_HISTORY, null, values);
    }

    public List<History> getAllHistory() {
        List<History> historyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY + " ORDER BY " + HISTORY_ID + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setId(cursor.getInt(0));
                history.setItems(cursor.getString(1));
                history.setTotal(cursor.getInt(2));
                history.setTanggal(cursor.getString(3));
                historyList.add(history);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return historyList;
    }

    // ==================== ORDER METHODS ====================
    public long createOrder(String userName, String standName, String items, int total, String paymentMethod, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_USER_NAME, userName);
        values.put(ORDER_STAND_NAME, standName);
        values.put(ORDER_ITEMS, items);
        values.put(ORDER_TOTAL, total);
        values.put(ORDER_STATUS, Order.STATUS_PENDING);
        values.put(ORDER_PAYMENT_METHOD, paymentMethod);
        values.put(ORDER_NOTES, notes);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        values.put(ORDER_CREATED_AT, sdf.format(new Date()));
        return db.insert(TABLE_ORDERS, null, values);
    }

    public void addOrderItem(int orderId, int menuId, String menuName, int qty, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_ITEM_ORDER_ID, orderId);
        values.put(ORDER_ITEM_MENU_ID, menuId);
        values.put(ORDER_ITEM_MENU_NAME, menuName);
        values.put(ORDER_ITEM_QTY, qty);
        values.put(ORDER_ITEM_PRICE, price);
        db.insert(TABLE_ORDER_ITEMS, null, values);
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " ORDER BY " + ORDER_ID + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                orders.add(createOrderFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }

    public Order getOrderById(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " WHERE " + ORDER_ID + " = ?",
                new String[]{String.valueOf(orderId)});

        Order order = null;
        if (cursor.moveToFirst()) {
            order = createOrderFromCursor(cursor);
        }
        cursor.close();
        return order;
    }

    private Order createOrderFromCursor(Cursor cursor) {
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ORDER_ID)));
        order.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_USER_NAME)));
        order.setStandName(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_STAND_NAME)));
        order.setItems(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_ITEMS)));
        order.setTotal(cursor.getInt(cursor.getColumnIndexOrThrow(ORDER_TOTAL)));
        order.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_STATUS)));
        order.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_CREATED_AT)));
        order.setPaymentMethod(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_PAYMENT_METHOD)));
        order.setNotes(cursor.getString(cursor.getColumnIndexOrThrow(ORDER_NOTES)));
        return order;
    }

    public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ORDER_ITEMS + " WHERE " + ORDER_ITEM_ORDER_ID + " = ?",
                new String[]{String.valueOf(orderId)});

        if (cursor.moveToFirst()) {
            do {
                OrderItem item = new OrderItem();
                item.setId(cursor.getInt(0));
                item.setOrderId(cursor.getInt(1));
                item.setMenuId(cursor.getInt(2));
                item.setMenuName(cursor.getString(3));
                item.setQty(cursor.getInt(4));
                item.setPrice(cursor.getInt(5));
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public void updateOrderStatus(int orderId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORDER_STATUS, status);
        db.update(TABLE_ORDERS, values, ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
    }

    // ==================== REVIEW METHODS ====================
    public void addReview(int menuId, String userName, int rating, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REVIEW_MENU_ID, menuId);
        values.put(REVIEW_USER_NAME, userName);
        values.put(REVIEW_RATING, rating);
        values.put(REVIEW_COMMENT, comment);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        values.put(REVIEW_CREATED_AT, sdf.format(new Date()));
        db.insert(TABLE_REVIEWS, null, values);
    }

    public List<Review> getMenuReviews(int menuId) {
        List<Review> reviews = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEWS + " WHERE " + REVIEW_MENU_ID + " = ? ORDER BY " + REVIEW_ID + " DESC",
                new String[]{String.valueOf(menuId)});

        if (cursor.moveToFirst()) {
            do {
                Review review = new Review();
                review.setId(cursor.getInt(0));
                review.setMenuId(cursor.getInt(1));
                review.setUserName(cursor.getString(2));
                review.setRating(cursor.getInt(3));
                review.setComment(cursor.getString(4));
                review.setCreatedAt(cursor.getString(5));
                reviews.add(review);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reviews;
    }

    // ==================== FAVORITES METHODS ====================
    public void addToFavorites(int menuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITES + " WHERE " + FAVORITE_MENU_ID + " = ?",
                new String[]{String.valueOf(menuId)});

        if (!cursor.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put(FAVORITE_MENU_ID, menuId);
            db.insert(TABLE_FAVORITES, null, values);
        }
        cursor.close();
    }

    public void addToFavorites(int userId, int menuId) {
        // For backward compatibility, userId is ignored for now
        addToFavorites(menuId);
    }

    public void removeFromFavorites(int menuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, FAVORITE_MENU_ID + " = ?", new String[]{String.valueOf(menuId)});
    }

    public void removeFromFavorites(int userId, int menuId) {
        // For backward compatibility, userId is ignored for now
        removeFromFavorites(menuId);
    }

    public boolean isFavorite(int menuId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITES + " WHERE " + FAVORITE_MENU_ID + " = ?",
                new String[]{String.valueOf(menuId)});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean isFavorite(int userId, int menuId) {
        // For backward compatibility, userId is ignored for now
        return isFavorite(menuId);
    }

    public List<Menu> getFavoriteMenus() {
        List<Menu> favorites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT m.* FROM " + TABLE_MENU + " m " +
                        "INNER JOIN " + TABLE_FAVORITES + " f ON m." + MENU_ID + " = f." + FAVORITE_MENU_ID,
                null);

        if (cursor.moveToFirst()) {
            do {
                Menu menu = createMenuFromCursor(cursor);
                favorites.add(menu);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favorites;
    }

    public List<Menu> getFavoriteMenus(int userId) {
        // For backward compatibility, userId is ignored for now
        return getFavoriteMenus();
    }

    // ==================== ADMIN STATISTICS METHODS ====================

    public int getTotalOrdersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_ORDERS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getPendingOrdersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_ORDERS + " WHERE " + ORDER_STATUS + " = ?",
                new String[]{Order.STATUS_PENDING});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getProcessingOrdersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_ORDERS + " WHERE " + ORDER_STATUS + " = ?",
                new String[]{Order.STATUS_PROCESSING});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getReadyOrdersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_ORDERS + " WHERE " + ORDER_STATUS + " = ?",
                new String[]{Order.STATUS_READY});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getCompletedOrdersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_ORDERS + " WHERE " + ORDER_STATUS + " = ?",
                new String[]{Order.STATUS_COMPLETED});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getTotalRevenue() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + ORDER_TOTAL + ") FROM " + TABLE_ORDERS +
                        " WHERE " + ORDER_STATUS + " IN (?, ?)",
                new String[]{Order.STATUS_COMPLETED, Order.STATUS_READY});
        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public int getTotalCustomersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(DISTINCT " + ORDER_USER_NAME + ") FROM " + TABLE_ORDERS,
                null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getTotalMenusCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_MENU, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int getAvailableMenusCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM " + TABLE_MENU + " WHERE " + MENU_AVAILABLE + " = 1",
                null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public List<Order> getOrdersByStatus(String status) {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDERS + " WHERE " + ORDER_STATUS + " = ? ORDER BY " + ORDER_ID + " DESC",
                new String[]{status});

        if (cursor.moveToFirst()) {
            do {
                orders.add(createOrderFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }

    public List<Order> getTodayOrders() {
        List<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String today = sdf.format(new Date());

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_ORDERS + " WHERE " + ORDER_CREATED_AT + " LIKE ? ORDER BY " + ORDER_ID + " DESC",
                new String[]{today + "%"});

        if (cursor.moveToFirst()) {
            do {
                orders.add(createOrderFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return orders;
    }

    public int getTodayRevenue() {
        SQLiteDatabase db = this.getReadableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String today = sdf.format(new Date());

        Cursor cursor = db.rawQuery(
                "SELECT SUM(" + ORDER_TOTAL + ") FROM " + TABLE_ORDERS +
                        " WHERE " + ORDER_CREATED_AT + " LIKE ? AND " + ORDER_STATUS + " IN (?, ?)",
                new String[]{today + "%", Order.STATUS_COMPLETED, Order.STATUS_READY});

        int total = 0;
        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        return total;
    }

    public Menu getMostOrderedMenu() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + ORDER_ITEM_MENU_ID + ", COUNT(*) as order_count " +
                        "FROM " + TABLE_ORDER_ITEMS +
                        " GROUP BY " + ORDER_ITEM_MENU_ID +
                        " ORDER BY order_count DESC LIMIT 1",
                null);

        Menu menu = null;
        if (cursor.moveToFirst()) {
            int menuId = cursor.getInt(0);
            menu = getMenuById(menuId);
        }
        cursor.close();
        return menu;
    }

    public List<Menu> getTopMenus(int limit) {
        List<Menu> topMenus = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT " + ORDER_ITEM_MENU_ID + ", SUM(" + ORDER_ITEM_QTY + ") as total_qty " +
                        "FROM " + TABLE_ORDER_ITEMS +
                        " GROUP BY " + ORDER_ITEM_MENU_ID +
                        " ORDER BY total_qty DESC LIMIT ?",
                new String[]{String.valueOf(limit)});

        if (cursor.moveToFirst()) {
            do {
                int menuId = cursor.getInt(0);
                Menu menu = getMenuById(menuId);
                if (menu != null) {
                    topMenus.add(menu);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return topMenus;
    }

    // ==================== USER AUTHENTICATION METHODS ====================

    public User login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_USERNAME + " = ? AND " + USER_PASSWORD + " = ?",
                new String[]{username, password});

        User user = null;
        if (cursor.moveToFirst()) {
            user = createUserFromCursor(cursor);
        }
        cursor.close();
        return user;
    }

    public long registerUser(String username, String password, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if username already exists
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_USERNAME + " = ?",
                new String[]{username});

        if (cursor.moveToFirst()) {
            cursor.close();
            return -1; // Username already exists
        }
        cursor.close();

        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, username);
        values.put(USER_PASSWORD, password);
        values.put(USER_NAME, name);
        values.put(USER_EMAIL, email);
        values.put(USER_PHONE, phone);
        values.put(USER_ROLE, User.ROLE_CUSTOMER);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        values.put(USER_CREATED_AT, sdf.format(new Date()));

        return db.insert(TABLE_USERS, null, values);
    }

    public User getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE " + USER_ID + " = ?",
                new String[]{String.valueOf(userId)});

        User user = null;
        if (cursor.moveToFirst()) {
            user = createUserFromCursor(cursor);
        }
        cursor.close();
        return user;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PHONE, user.getPhone());
        db.update(TABLE_USERS, values, USER_ID + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public void changePassword(int userId, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_PASSWORD, newPassword);
        db.update(TABLE_USERS, values, USER_ID + " = ?", new String[]{String.valueOf(userId)});
    }

    private User createUserFromCursor(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USER_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)));
        user.setName(cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)));
        user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(USER_PHONE)));
        user.setRole(cursor.getString(cursor.getColumnIndexOrThrow(USER_ROLE)));
        user.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow(USER_CREATED_AT)));
        return user;
    }
}