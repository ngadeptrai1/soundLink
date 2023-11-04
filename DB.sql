CREATE DATABASE DUAN1

USE DUAN1

DROP DATABASE DUAN1

 
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Product_Images]
(
    [Product_Images_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Product_Details_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Product_Images_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]


GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Color]
(
    [Color_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Color] [NVARCHAR](30) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Color_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]




GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Product_Details]
(
    [Product_Details_Id] [INT] IDENTITY(1,1) NOT NULL ,
    [Color] [NVARCHAR](20) NOT NULL,
    [Product_id] [INT] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Activated] [BIT] NOT NULL,
    [Product_price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Product_Details_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Type_Product]
(
    [Type_Product_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50),
    [Description] [NVARCHAR](100) NOT NULL,
    [Update_Day] [DATE] NOT NULL,
    [Quantity] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Type_Product_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Brands]
(
    [Brand_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Brand_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Designs]
(
    [Design_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Design_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Product]
(
    [Product_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Thumnails] [NVARCHAR](30) NOT NULL,
    [Total_Quantity] [INT] NOT NULL,
    [Brand_Id] [INT] NOT NULL,
    [Type_Product_Id] [INT] NOT NULL,
    [Category_Id] [INT] NOT NULL,
    [Design_Id] [INT] NOT NULL,
    [Updated_Time] [DATE] NOT NULL,
    [Updated_Person_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Product_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Category]
(
    [Categories_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Describe] [NVARCHAR](100) NOT NULL,
    [Updated_Time] [DATE] NOT NULL,
    [Updated_Person_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Categories_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Warranty]
(
    [Warranty_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Duration] [INT] NOT NULL,
    [Start_Date] [DATE] NOT NULL,
    [End_Date] [DATE] NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE TABLE [dbo].[Product_Warranly]
(
    [Product_Warranly_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Product_Id] [INT] NOT NULL,
    [Warranty_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Warranty_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Invoices_Details]
(
    [Invoices_Details_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Invoice_Id] [INT] NOT NULL,
    [Product_Id] [INT] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Invoices_Details_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Code_Coupons]
(
    [Code_Coupons_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Code] [INT] NOT NULL,
    [Activated] [BIT]NOT NULL,
    [Coupons_Id] [INT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Code_Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Coupons]
(
    [Coupons_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Describe] [NVARCHAR](100) NOT NULL,
    [Start_Day] [DATE] NOT NULL,
    [End_Date] [DATE] NOT NULL,
    [Quantity] [INT] NOT NULL,
    [Promotional_Type] [BIT] NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Attribute] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Coupons_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Pays]
(
    [Pay_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Pay_Method_Id] [VARCHAR](10) NOT NULL,
    [Clent_Id] [INT] NOT NULL,
    [Invoice_Id] [INT] NOT NULL,
    [Status] [NVARCHAR](50) NOT NULL,
    [Money] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Pay_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO



CREATE TABLE [dbo].[Invoices]
(
    [Invoice_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Created_Time] [DATE] NOT NULL,
    [Created_Person_Id] [INT] NOT NULL,
    [Client_Id] [INT] NOT NULL,
    [Origin_Price] [FLOAT] NOT NULL,
    [Attribute_Discount] [NVARCHAR](50) NOT NULL,
    [Note] [NVARCHAR](50) NOT NULL,
    [Status_Invoice_Id] [VARCHAR](10) NOT NULL,
    [Total_Quantity] [INT] NOT NULL,
    [Discount_Price] [FLOAT] NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Users]
(
    [User_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Birthday] [DATE] NOT NULL,
    [Gender] [NVARCHAR](20) NOT NULL,
    [Phone_number] [NVARCHAR](10) NOT NULL,
    [Address] [NVARCHAR](50) NOT NULL,
    [Email] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[User_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Account]
(
    [Account_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Username] [NVARCHAR](30) NOT NULL,
    [Password] [NVARCHAR](30) NOT NULL,
    [User_Id] [INT] NOT NULL,
    [Date_Created] [DATE] NOT NULL,
    [Role_Id] [VARCHAR](10) NOT NULL,
    [Update_Day] [DATE] NOT NULL,
    [Activated] [BIT]NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Account_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Status_Invoice]
(
    [Status_Invoice_Id] [VARCHAR](10) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED 
(
	[Status_Invoice_Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Client]
(
    [Client_Id] [INT] IDENTITY(1,1) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Phone_Number] [NVARCHAR](10) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Client_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Pay_Method]
(
    [Pay_Method_Id] [VARCHAR](10) NOT NULL,
    [Method] [NVARCHAR](30) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    [Content] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Pay_Method_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Role]
(
    [Role_Id] [VARCHAR](10) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Status] [NVARCHAR](30) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Role_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[Store]
(
    [Store_Id] [VARCHAR](10) NOT NULL,
    [Name] [NVARCHAR](50) NOT NULL,
    [Description] [NVARCHAR](100) NOT NULL,
    [Address] [NVARCHAR](50) NOT NULL,
    PRIMARY KEY CLUSTERED
(
	[Store_Id] ASC
)
WITH
(PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]



ALTER TABLE Product_Images
ADD CONSTRAINT FK_Product_Images_Product_Details
FOREIGN KEY (Product_Details_Id) REFERENCES Product_Details(Product_Details_Id);


ALTER TABLE Product_Details
ADD CONSTRAINT FK_Product_Product_Details
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Type_Product_Product_Details
FOREIGN KEY (Type_Product_Id) REFERENCES Type_Product(Type_Product_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Product_Brands
FOREIGN KEY (Brand_Id) REFERENCES Brands(Brand_Id);

ALTER TABLE Product
ADD CONSTRAINT FK_Product_Designs
FOREIGN KEY (Design_Id) REFERENCES Designs(Design_Id);

ALTER TABLE Product_Warranly
ADD CONSTRAINT FK_Product_Warranly_Product
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Product_Warranly
ADD CONSTRAINT FK_Product_Warranly_Warranly
FOREIGN KEY (Warranty_Id) REFERENCES Warranty(Warranty_Id);


ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);



ALTER TABLE Invoices_Details
ADD CONSTRAINT FK_Invoices_Details_Product
FOREIGN KEY (Product_Id) REFERENCES Product(Product_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Client
FOREIGN KEY (Client_Id) REFERENCES Client(Client_Id);

ALTER TABLE Invoices
ADD CONSTRAINT FK_Invoices_Status_Invoices
FOREIGN KEY (Status_Invoice_Id) REFERENCES Status_Invoice(Status_Invoice_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Pay_Method
FOREIGN KEY (Pay_Method_Id) REFERENCES Pay_Method(Pay_Method_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Client
FOREIGN KEY (Clent_Id) REFERENCES Client(Client_Id);

ALTER TABLE Pays
ADD CONSTRAINT FK_Pays_Invoices
FOREIGN KEY (Invoice_Id) REFERENCES Invoices(Invoice_Id);


ALTER TABLE Account
ADD CONSTRAINT FK_Account_Users
FOREIGN KEY (User_Id) REFERENCES Users(User_Id);

ALTER TABLE Account
ADD CONSTRAINT FK_Account_Role
FOREIGN KEY (Role_Id) REFERENCES Role(Role_Id);




INSERT INTO Color
    ( Color, Status)
VALUES
    ( 'Red', 'Active'),
    ( 'Blue', 'Active'),
    ( 'Green', 'Active'),
    ( 'Black', 'Active'),
    ( 'White', 'Active'),
    ( 'Yellow', 'Active'),
    ( 'Orange', 'Active'),
    ( 'Pink', 'Active'),
    ( 'Brown', 'Active'),
    ( 'Purple', 'Active'),
    ( 'Gray', 'Active'),
    ( 'Cyan', 'Active'),
    ( 'Magenta', 'Active'),
    ( 'Lime', 'Active'),
    ( 'Turquoise', 'Active');




INSERT INTO Brands
    (Name, Date_Created, Description, Activated)
VALUES
    ( 'Brand 1', '2023-11-02', 'Description 1', 1),
    ( 'Brand 2', '2023-11-02', 'Description 2', 1),
    ( 'Brand 3', '2023-11-02', 'Description 3', 1),
    ( 'Brand 4', '2023-11-02', 'Description 4', 1),
    ( 'Brand 5', '2023-11-02', 'Description 5', 1),
    ( 'Brand 6', '2023-11-02', 'Description 6', 0),
    ( 'Brand 7', '2023-11-02', 'Description 7', 1),
    ( 'Brand 8', '2023-11-02', 'Description 8', 1),
    ( 'Brand 9', '2023-11-02', 'Description 9', 1),
    ( 'Brand 10', '2023-11-02', 'Description 10', 1),
    ( 'Brand 11', '2023-11-02', 'Description 11', 1),
    ( 'Brand 12', '2023-11-02', 'Description 12', 1),
    ( 'Brand 13', '2023-11-02', 'Description 13', 0),
    ( 'Brand 14', '2023-11-02', 'Description 14', 1),
    ( 'Brand 15', '2023-11-02', 'Description 15', 1);

INSERT INTO Designs
    ( Name, Description, Activated)
VALUES
    ( 'Design 1', 'Description 1', 1),
    ( 'Design 2', 'Description 2', 1),
    ( 'Design 3', 'Description 3', 1),
    ( 'Design 4', 'Description 4', 1),
    ( 'Design 5', 'Description 5', 1),
    ( 'Design 6', 'Description 6', 0),
    ( 'Design 7', 'Description 7', 1),
    ( 'Design 8', 'Description 8', 1),
    ( 'Design 9', 'Description 9', 1),
    ( 'Design 10', 'Description 10', 1),
    ( 'Design 11', 'Description 11', 1),
    ( 'Design 12', 'Description 12', 1),
    ( 'Design 13', 'Description 13', 0),
    ( 'Design 14', 'Description 14', 1),
    ( 'Design 15', 'Description 15', 1);

INSERT INTO Category
    ( Name, Describe, Updated_Time, Updated_Person_Id)
VALUES
    ( 'Category 1', 'Description 1', '2023-11-02', 1),
    ( 'Category 2', 'Description 2', '2023-11-02', 2),
    ( 'Category 3', 'Description 3', '2023-11-02', 3),
    ( 'Category 4', 'Description 4', '2023-11-02', 4),
    ('Category 5', 'Description 5', '2023-11-02', 5),
    ( 'Category 6', 'Description 6', '2023-11-02', 6),
    ('Category 7', 'Description 7', '2023-11-02', 7),
    ( 'Category 8', 'Description 8', '2023-11-02', 8),
    ('Category 9', 'Description 9', '2023-11-02', 9),
    ( 'Category 10', 'Description 10', '2023-11-02', 10),
    ( 'Category 11', 'Description 11', '2023-11-02', 11),
    ( 'Category 12', 'Description 12', '2023-11-02', 12),
    ( 'Category 13', 'Description 13', '2023-11-02', 13),
    ( 'Category 14', 'Description 14', '2023-11-02', 14),
    ( 'Category 15', 'Description 15', '2023-11-02', 15);

INSERT INTO Type_Product
    ( Name, Description, Update_Day, Quantity)
VALUES
    ( 'Type 1', 'Description 1', '2023-11-02', 10),
    ( 'Type 2', 'Description 2', '2023-11-02', 15),
    ('Type 3', 'Description 3', '2023-11-02', 20),
    ( 'Type 4', 'Description 4', '2023-11-02', 25),
    ( 'Type 5', 'Description 5', '2023-11-02', 30),
    ( 'Type 6', 'Description 6', '2023-11-02', 35),
    ( 'Type 7', 'Description 7', '2023-11-02', 40),
    ( 'Type 8', 'Description 8', '2023-11-02', 45),
    ( 'Type 9', 'Description 9', '2023-11-02', 50),
    ('Type 10', 'Description 10', '2023-11-02', 55),
    ( 'Type 11', 'Description 11', '2023-11-02', 60),
    ( 'Type 12', 'Description 12', '2023-11-02', 65),
    ( 'Type 13', 'Description 13', '2023-11-02', 70),
    ( 'Type 14', 'Description 14', '2023-11-02', 75),
    ( 'Type 15', 'Description 15', '2023-11-02', 80);


INSERT INTO Product
    ( Name, Description, Status, Thumnails, Total_Quantity, Brand_Id, Type_Product_Id, Category_Id, Design_Id, Updated_Time, Updated_Person_Id)
VALUES
    ( 'Product 1', 'Description 1', 'Active', 'Thumb1.jpg', 100, 1, 1, 1, 1, '2023-11-02', 1),
    ( 'Product 2', 'Description 2', 'Active', 'Thumb2.jpg', 120, 2, 2, 2, 2, '2023-11-02', 2),
    ( 'Product 3', 'Description 3', 'Active', 'Thumb3.jpg', 90, 3, 3, 3, 3, '2023-11-02', 3),
    ( 'Product 4', 'Description 4', 'Inactive', 'Thumb4.jpg', 150, 4, 4, 4, 4, '2023-11-02', 4),
    ( 'Product 5', 'Description 5', 'Active', 'Thumb5.jpg', 110, 5, 5, 5, 5, '2023-11-02', 5),
    ( 'Product 6', 'Description 6', 'Active', 'Thumb6.jpg', 130, 6, 6, 6, 6, '2023-11-02', 6),
    ( 'Product 7', 'Description 7', 'Active', 'Thumb7.jpg', 140, 7, 7, 7, 7, '2023-11-02', 7),
    ( 'Product 8', 'Description 8', 'Active', 'Thumb8.jpg', 95, 8, 8, 8, 8, '2023-11-02', 8),
    ( 'Product 9', 'Description 9', 'Inactive', 'Thumb9.jpg', 125, 9, 9, 9, 9, '2023-11-02', 9),
    ( 'Product 10', 'Description 10', 'Active', 'Thumb10.jpg', 160, 10, 10, 10, 10, '2023-11-02', 10),
    ( 'Product 11', 'Description 11', 'Active', 'Thumb11.jpg', 105, 11, 11, 11, 11, '2023-11-02', 11),
    ( 'Product 12', 'Description 12', 'Inactive', 'Thumb12.jpg', 145, 12, 12, 12, 12, '2023-11-02', 12),
    ( 'Product 13', 'Description 13', 'Active', 'Thumb13.jpg', 115, 13, 13, 13, 13, '2023-11-02', 13),
    ( 'Product 14', 'Description 14', 'Active', 'Thumb14.jpg', 135, 14, 14, 14, 14, '2023-11-02', 14),
    ( 'Product 15', 'Description 15', 'Active', 'Thumb15.jpg', 155, 15, 15, 15, 15, '2023-11-02', 15);



INSERT INTO Product_Details
    ( Color, Product_Id, Quantity, Activated, Product_price)
VALUES
    ( 'Red', 1, 10, 0, 100.0),
    ( 'Blue', 2, 20, 1, 120.0),
    ( 'Green', 3, 15, 0, 90.0),
    ( 'Black', 4, 30, 1, 150.0),
    ( 'White', 5, 25, 0, 110.0),
    ( 'Yellow', 6, 12, 1, 130.0),
    ( 'Orange', 7, 18, 0, 140.0),
    ( 'Pink', 8, 22, 0, 95.0),
    ( 'Brown', 9, 28, 1, 125.0),
    ( 'Purple', 10, 35, 0, 160.0),
    ( 'Gray', 11, 17, 0, 105.0),
    ( 'Cyan', 12, 14, 1, 145.0),
    ( 'Magenta', 13, 21, 0, 115.0),
    ( 'Lime', 14, 26, 1, 135.0),
    ( 'Turquoise', 15, 19, 0, 155.0);

INSERT INTO Product_Images
    (Name, Product_Details_Id)
VALUES
    ( 'Image 1', 1),
    ( 'Image 2', 2),
    ( 'Image 3', 3),
    ( 'Image 4', 4),
    ( 'Image 5', 5),
    ( 'Image 6', 6),
    ( 'Image 7', 7),
    ( 'Image 8', 8),
    ( 'Image 9', 9),
    ( 'Image 10', 10),
    ( 'Image 11', 11),
    ( 'Image 12', 12),
    ( 'Image 13', 13),
    ( 'Image 14', 14),
    ( 'Image 15', 15);


INSERT INTO Warranty
    ( Status, Duration, Start_Date, End_Date, Activated)
VALUES
    ( 'Active', 12, '2023-11-02', '2024-11-02', 1),
    ( 'Active', 24, '2023-11-02', '2025-11-02', 1),
    ( 'Active', 36, '2023-11-02', '2026-11-02', 1),
    ( 'Active', 6, '2023-11-02', '2024-05-02', 1),
    ( 'Active', 18, '2023-11-02', '2024-05-02', 1),
    ( 'Active', 12, '2023-11-02', '2024-11-02', 0),
    ( 'Active', 24, '2023-11-02', '2025-11-02', 1),
    ( 'Active', 36, '2023-11-02', '2026-11-02', 1),
    ( 'Active', 6, '2023-11-02', '2024-05-02', 1),
    ( 'Active', 18, '2023-11-02', '2024-05-02', 1),
    ( 'Active', 12, '2023-11-02', '2024-11-02', 1),
    ( 'Active', 24, '2023-11-02', '2025-11-02', 1),
    ( 'Active', 36, '2023-11-02', '2026-11-02', 0),
    ( 'Active', 6, '2023-11-02', '2024-05-02', 1),
    ( 'Active', 18, '2023-11-02', '2024-05-02', 1);

INSERT INTO Product_Warranly
    ( Product_Id, Warranty_Id)
VALUES
    ( 1, 1),
    ( 2, 2),
    ( 3, 3),
    ( 4, 4),
    ( 5, 5),
    ( 6, 6),
    ( 7, 7),
    ( 8, 8),
    ( 9, 9),
    ( 10, 10),
    ( 11, 11),
    ( 12, 12),
    ( 13, 13),
    ( 14, 14),
    ( 15, 15);





INSERT INTO Status_Invoice
    (Status_Invoice_Id, Status)
VALUES
    ('1', 'Pending'),
    ('2', 'Paid'),
    ('3', 'Shipped'),
    ('4', 'Delivered'),
    ('5', 'Cancelled'),
    ('6', 'Refunded'),
    ('7', 'On Hold'),
    ('8', 'Processing'),
    ('9', 'In Progress'),
    ('10', 'Completed'),
    ('11', 'Confirmed'),
    ('12', 'Awaiting Payment'),
    ('13', 'Reviewing'),
    ('14', 'Approved'),
    ('15', 'Rejected');


INSERT INTO Client
    ( Name, Phone_Number)
VALUES
    ( 'Client 1', '1234567890'),
    ( 'Client 2', '2345678901'),
    ( 'Client 3', '3456789012'),
    ( 'Client 4', '4567890123'),
    ( 'Client 5', '5678901234'),
    ( 'Client 6', '6789012345'),
    ( 'Client 7', '7890123456'),
    ( 'Client 8', '8901234567'),
    ( 'Client 9', '9012345678'),
    ( 'Client 10', '0123456789'),
    ( 'Client 11', '1234567890'),
    ( 'Client 12', '2345678901'),
    ( 'Client 13', '3456789012'),
    ( 'Client 14', '4567890123'),
    ( 'Client 15', '5678901234');



INSERT INTO Invoices
    (Created_Time, Created_Person_Id, Client_Id, Origin_Price, Attribute_Discount, Note, Status_Invoice_Id, Total_Quantity, Discount_Price)
VALUES
    ('2023-11-02', 1, 1, 150.00, 'Discount Type A', 'Order 1', 1, 2, 20.00),
    ( '2023-11-03', 2, 2, 200.00, 'Discount Type B', 'Order 2', 2, 3, 30.00),
    ( '2023-11-04', 3, 3, 100.00, 'Discount Type A', 'Order 3', 3, 1, 10.00),
    ( '2023-11-05', 4, 4, 250.00, 'Discount Type C', 'Order 4', 4, 4, 40.00),
    ( '2023-11-06', 5, 5, 150.00, 'Discount Type B', 'Order 5', 5, 2, 20.00),
    ( '2023-11-07', 6, 6, 200.00, 'Discount Type A', 'Order 6', 6, 3, 30.00),
    ( '2023-11-08', 7, 7, 100.00, 'Discount Type C', 'Order 7', 7, 1, 10.00),
    ( '2023-11-09', 8, 8, 250.00, 'Discount Type B', 'Order 8', 8, 4, 40.00),
    ( '2023-11-10', 9, 9, 150.00, 'Discount Type A', 'Order 9', 9, 2, 20.00),
    ( '2023-11-11', 10, 10, 200.00, 'Discount Type B', 'Order 10', 10, 3, 30.00),
    ( '2023-11-12', 11, 11, 100.00, 'Discount Type C', 'Order 11', 11, 1, 10.00),
    ( '2023-11-13', 12, 12, 250.00, 'Discount Type A', 'Order 12', 12, 4, 40.00),
    ( '2023-11-14', 13, 13, 150.00, 'Discount Type B', 'Order 13', 13, 2, 20.00),
    ( '2023-11-15', 14, 14, 200.00, 'Discount Type A', 'Order 14', 14, 3, 30.00),
    ( '2023-11-16', 15, 15, 100.00, 'Discount Type C', 'Order 15', 15, 1, 10.00);

INSERT INTO Invoices_Details
    ( Invoice_Id, Product_Id, Quantity, Price)
VALUES
    ( 1, 1, 2, 50.00),
    ( 2, 2, 3, 75.00),
    ( 3, 3, 1, 30.00),
    ( 4, 4, 4, 100.00),
    ( 5, 5, 2, 50.00),
    ( 6, 6, 3, 75.00),
    ( 7, 7, 1, 30.00),
    ( 8, 8, 4, 100.00),
    ( 9, 9, 2, 50.00),
    ( 10, 10, 3, 75.00),
    ( 11, 11, 1, 30.00),
    ( 12, 12, 4, 100.00),
    ( 13, 13, 2, 50.00),
    ( 14, 14, 3, 75.00),
    ( 15, 15, 1, 30.00);

INSERT INTO Code_Coupons
    ( Code, Activated, Coupons_Id)
VALUES
    ( 12345, 1, 1),
    ( 67890, 1, 2),
    ( 54321, 0, 3),
    ( 98765, 1, 4),
    ( 11111, 0, 5),
    ( 22222, 1, 6),
    ( 33333, 1, 7),
    ( 44444, 0, 8),
    ( 55555, 1, 9),
    ( 66666, 1, 10),
    ( 77777, 0, 11),
    ( 88888, 1, 12),
    ( 99999, 1, 13),
    ( 00000, 0, 14),
    ( 12121, 1, 15);



INSERT INTO Coupons
    ( Describe, Start_Day, End_Date, Quantity, Promotional_Type, Status, Attribute)
VALUES
    ( 'Coupon 1', '2023-11-02', '2023-12-02', 100, 1, 'Active', 'Type A'),
    ( 'Coupon 2', '2023-11-02', '2023-12-02', 50, 0, 'Active', 'Type B'),
    ( 'Coupon 3', '2023-11-02', '2023-12-02', 200, 1, 'Active', 'Type A'),
    ( 'Coupon 4', '2023-11-02', '2023-12-02', 75, 1, 'Active', 'Type C'),
    ( 'Coupon 5', '2023-11-02', '2023-12-02', 150, 0, 'Active', 'Type B'),
    ( 'Coupon 6', '2023-11-02', '2023-12-02', 300, 1, 'Active', 'Type A'),
    ( 'Coupon 7', '2023-11-02', '2023-12-02', 50, 1, 'Active', 'Type C'),
    ( 'Coupon 8', '2023-11-02', '2023-12-02', 200, 0, 'Active', 'Type B'),
    ( 'Coupon 9', '2023-11-02', '2023-12-02', 100, 1, 'Active', 'Type A'),
    ( 'Coupon 10', '2023-11-02', '2023-12-02', 250, 0, 'Active', 'Type B'),
    ( 'Coupon 11', '2023-11-02', '2023-12-02', 50, 1, 'Active', 'Type C'),
    ( 'Coupon 12', '2023-11-02', '2023-12-02', 150, 1, 'Active', 'Type A'),
    ( 'Coupon 13', '2023-11-02', '2023-12-02', 300, 0, 'Active', 'Type B'),
    ( 'Coupon 14', '2023-11-02', '2023-12-02', 75, 1, 'Active', 'Type A'),
    ( 'Coupon 15', '2023-11-02', '2023-12-02', 200, 1, 'Active', 'Type C');


INSERT INTO Pay_Method
    (Pay_Method_Id, Method, Status, Content)
VALUES
    ('1', 'Credit Card', 'Active', 'Accepts credit card payments.'),
    ('2', 'Cash On Delivery', 'Active', 'Payment is made in cash upon delivery.'),
    ('3', 'Bank Transfer', 'Active', 'Payment is made via bank transfer.'),
    ('4', 'PayPal', 'Active', 'Accepts PayPal payments.'),
    ('5', 'Online Payment Gateway', 'Active', 'Payment is made through an online payment gateway.'),
    ('6', 'Cryptocurrency', 'Active', 'Accepts cryptocurrency payments.'),
    ('7', 'Mobile Wallet', 'Active', 'Payment is made through a mobile wallet.'),
    ('8', 'Check', 'Active', 'Accepts check payments.'),
    ('9', 'Payment Plan', 'Active', 'Offers payment plans for customers.'),
    ('10', 'Other', 'Active', 'Other payment methods.'),
    ('11', 'Bank Draft', 'Active', 'Accepts bank draft payments.'),
    ('12', 'Money Order', 'Active', 'Accepts money order payments.'),
    ('13', 'Online Bank Transfer', 'Active', 'Payment is made through online bank transfer.'),
    ('14', 'Google Wallet', 'Active', 'Accepts Google Wallet payments.'),
    ('15', 'Apple Pay', 'Active', 'Accepts Apple Pay payments.');

INSERT INTO Pays
    ( Pay_Method_Id, Clent_Id, Invoice_Id, Status, Money)
VALUES
    ( '1', 1, 1, 'Paid', 50.00),
    ( '2', 2, 2, 'Paid', 75.00),
    ( '3', 3, 3, 'Paid', 30.00),
    ( '4', 4, 4, 'Paid', 100.00),
    ( '5', 5, 5, 'Paid', 50.00),
    ( '6', 6, 6, 'Paid', 75.00),
    ( '7', 7, 7, 'Paid', 30.00),
    ( '8', 8, 8, 'Paid', 100.00),
    ( '9', 9, 9, 'Paid', 50.00),
    ( '10', 10, 10, 'Paid', 75.00),
    ( '11', 11, 11, 'Paid', 30.00),
    ( '12', 12, 12, 'Paid', 100.00),
    ( '13', 13, 13, 'Paid', 50.00),
    ( '14', 14, 14, 'Paid', 75.00),
    ( '15', 15, 15, 'Paid', 30.00);



INSERT INTO Users
    ( Name, Birthday, Gender, Phone_number, Address, Email)
VALUES
    ( 'User 1', '1990-01-01', 'Male', '1234567890', '123 Street, City', 'user1@example.com'),
    ( 'User 2', '1991-02-02', 'Female', '2345678901', '456 Street, City', 'user2@example.com'),
    ( 'User 3', '1992-03-03', 'Male', '3456789012', '789 Street, City', 'user3@example.com'),
    ( 'User 4', '1993-04-04', 'Female', '4567890123', '012 Street, City', 'user4@example.com'),
    ( 'User 5', '1994-05-05', 'Male', '5678901234', '345 Street, City', 'user5@example.com'),
    ( 'User 6', '1995-06-06', 'Female', '6789012345', '678 Street, City', 'user6@example.com'),
    ( 'User 7', '1996-07-07', 'Male', '7890123456', '901 Street, City', 'user7@example.com'),
    ( 'User 8', '1997-08-08', 'Female', '8901234567', '234 Street, City', 'user8@example.com'),
    ( 'User 9', '1998-09-09', 'Male', '9012345678', '567 Street, City', 'user9@example.com'),
    ( 'User 10', '1999-10-10', 'Female', '0123456789', '890 Street, City', 'user10@example.com'),
    ('User 11', '2000-11-11', 'Male', '1234567890', '123 Street, City', 'user11@example.com'),
    ( 'User 12', '2001-12-12', 'Female', '2345678901', '456 Street, City', 'user12@example.com'),
    ( 'User 13', '2002-01-13', 'Male', '3456789012', '789 Street, City', 'user13@example.com'),
    ( 'User 14', '2003-02-14', 'Female', '4567890123', '012 Street, City', 'user14@example.com'),
    ('User 15', '2004-03-15', 'Male', '5678901234', '345 Street, City', 'user15@example.com');



INSERT INTO Role
    (Role_Id, Name, Status)
VALUES
    ('1', 'Admin', 'Active'),
    ('2', 'Manager', 'Active'),
    ('3', 'Salesperson', 'Active'),
    ('4', 'Customer Service', 'Active'),
    ('5', 'Accountant', 'Active'),
    ('6', 'Marketing', 'Active'),
    ('7', 'IT Support', 'Active'),
    ('8', 'Warehouse Staff', 'Active'),
    ('9', 'Quality Control', 'Active'),
    ('10', 'Human Resources', 'Active'),
    ('11', 'Supervisor', 'Active'),
    ('12', 'Team Lead', 'Active'),
    ('13', 'Project Manager', 'Active'),
    ('14', 'Developer', 'Active'),
    ('15', 'Designer', 'Active');



INSERT INTO Account
    ( Username, Password, User_Id, Date_Created, Role_Id, Update_Day, Activated)
VALUES
    ( 'user1', 'password1', 1, '2023-11-02', 1, '2023-11-02', 1),
    ( 'user2', 'password2', 2, '2023-11-03', 2, '2023-11-03', 1),
    ( 'user3', 'password3', 3, '2023-11-04', 3, '2023-11-04', 1),
    ( 'user4', 'password4', 4, '2023-11-05', 4, '2023-11-05', 0),
    ( 'user5', 'password5', 5, '2023-11-06', 5, '2023-11-06', 1),
    ( 'user6', 'password6', 6, '2023-11-07', 6, '2023-11-07', 1),
    ( 'user7', 'password7', 7, '2023-11-08', 7, '2023-11-08', 0),
    ( 'user8', 'password8', 8, '2023-11-09', 8, '2023-11-09', 1),
    ( 'user9', 'password9', 9, '2023-11-10', 9, '2023-11-10', 1),
    ( 'user10', 'password10', 10, '2023-11-11', 10, '2023-11-11', 1),
    ( 'user11', 'password11', 11, '2023-11-12', 11, '2023-11-12', 1),
    ( 'user12', 'password12', 12, '2023-11-13', 12, '2023-11-13', 0),
    ( 'user13', 'password13', 13, '2023-11-14', 13, '2023-11-14', 1),
    ( 'user14', 'password14', 14, '2023-11-15', 14, '2023-11-15', 1),
    ( 'user15', 'password15', 15, '2023-11-16', 15, '2023-11-16', 1);

INSERT INTO Store
    (Store_Id, Name, Description, Address)
VALUES
    ('1', 'Store 1', 'Description of Store 1', 'Address of Store 1'),
    ('2', 'Store 2', 'Description of Store 2', 'Address of Store 2'),
    ('3', 'Store 3', 'Description of Store 3', 'Address of Store 3'),
    ('4', 'Store 4', 'Description of Store 4', 'Address of Store 4'),
    ('5', 'Store 5', 'Description of Store 5', 'Address of Store 5'),
    ('6', 'Store 6', 'Description of Store 6', 'Address of Store 6'),
    ('7', 'Store 7', 'Description of Store 7', 'Address of Store 7'),
    ('8', 'Store 8', 'Description of Store 8', 'Address of Store 8'),
    ('9', 'Store 9', 'Description of Store 9', 'Address of Store 9'),
    ('10', 'Store 10', 'Description of Store 10', 'Address of Store 10'),
    ('11', 'Store 11', 'Description of Store 11', 'Address of Store 11'),
    ('12', 'Store 12', 'Description of Store 12', 'Address of Store 12'),
    ('13', 'Store 13', 'Description of Store 13', 'Address of Store 13'),
    ('14', 'Store 14', 'Description of Store 14', 'Address of Store 14'),
    ('15', 'Store 15', 'Description of Store 15', 'Address of Store 15');
