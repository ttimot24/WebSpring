/**
 * Author:  Timot Tarjani
 * Created: 2018.12.23.
 */
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `permission` int(11) NOT NULL,
  `rights` text
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--
    INSERT INTO `user_roles` (`id`, `name`, `permission`, `rights`) VALUES
    (1, 'Public', 0, NULL),
    (2, 'User', 1, NULL),
    (3, 'Member', 2, NULL),
    (4, 'Editor', 3, '["admin_area","blogpost","page","media"]'),
    (5, 'Manager', 4, '["admin_area","blogpostcategory","blogpostcomment","blogpost","page","search","user"]'),
    (6, 'Admin', 5, '["admin_area","blogpostcategory","blogpostcomment","blogpost","dashboard","filemanager","headerimage","page","plugin","search","settings","theme","user","userrole"]');
    --
    --
    ALTER TABLE `user_roles`
        ADD PRIMARY KEY (`id`);
    --
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `slug` varchar(255),
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT '2',
  `visits` int(11) NOT NULL DEFAULT '0',
  `image` varchar(255) DEFAULT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
--
-- A tábla indexei `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_username_unique` (`username`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

-- AUTO_INCREMENT a táblához `users`
--
ALTER TABLE `users` MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT;